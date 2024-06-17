package com.parse.steam.services;

import com.parse.steam.converters.TVConverter;
import com.parse.steam.dtos.TVDto;
import com.parse.steam.dtos.stat.PredicateDto;
import com.parse.steam.entities.*;
import com.parse.steam.exceptions.ElementNotFoundException;
import com.parse.steam.repo.TVMarketRepo;
import com.parse.steam.repo.TVRepo;
import com.parse.steam.repo.TVStatRepo;
import com.parse.steam.utils.builders.MonitorBuilder;
import com.parse.steam.utils.builders.TVBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TVService {
    private final TVRepo tvRepo;
    private final TVMarketRepo tvMarketRepo;
    private final TVStatRepo tvStatRepo;

    @Value("${coefficient.middle.buff}")
    private Double coefficientMiddleBuff;
    @Value("${coefficient.middle.debuff}")
    private Double coefficientMiddleDebuff;

    @Value("${coefficient.price-info.buff}")
    private Double coefficientPriceInfoBuff;
    @Value("${coefficient.price-info.debuff}")
    private Double coefficientPriceInfoDebuff;

    @Value("${coefficient.correct.buff}")
    private Double coefficientCorrectBuff;
    @Value("${coefficient.correct.debuff}")
    private Double coefficientCorrectDebuff;

    @Value("${coefficient.percent.up}")
    private Double percentUp;
    @Value("${coefficient.percent.down}")
    private Double percentDown;
    @Value("${coefficient.percent.rating}")
    private Double percentRating;

    public TVDto saveTV(TVDto dto) {
        return TVConverter.toDto(tvRepo.save(TVConverter.toEntity(dto)));
    }

    public List<TVDto> saveTVList(List<TVDto> dtoList) {
        return tvRepo.saveAll(dtoList.stream().map(TVConverter::toEntity).toList()).stream().map(TVConverter::toDto).toList();
    }

    public TVDto archiveTV(Long id) throws ElementNotFoundException {
        TVEntity entity = tvRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("TV not found"));
        entity.setArchived(true);
        return TVConverter.toDto(tvRepo.save(entity));
    }

    public TVDto unarchiveTV(Long id) throws ElementNotFoundException {
        TVEntity entity = tvRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("TV not found"));
        entity.setArchived(false);
        return TVConverter.toDto(tvRepo.save(entity));
    }

    public TVDto updateTV(TVDto dto) throws ElementNotFoundException {
        TVEntity entity = tvRepo.findById(dto.getId()).orElseThrow(() -> new ElementNotFoundException("TV not found"));
        TVEntity updatedEntity = TVBuilder.buildUpdatedTVEntity(entity, TVConverter.toEntity(dto));
        updatedEntity = tvRepo.save(updatedEntity);
        return TVConverter.toDto(updatedEntity);
    }

    public TVDto getTVById(Long id) throws ElementNotFoundException {
        return TVConverter.toDto(tvRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("TV not found")));
    }

    public List<TVDto> getAllTV() {
        return tvRepo.findAll().stream().map(TVConverter::toDto).toList();
    }

    public List<PredicateDto> predicateUpPrice(Long itemId) {
        TVEntity tvEntity = tvRepo.findById(itemId).orElse(null);

        if (tvEntity == null || tvEntity.getArchived()) {
            return new ArrayList<>();
        }

        List<PredicateDto> response = new ArrayList<>();
        List<TVMarketEntity> tvMarketEntityList = tvMarketRepo.findAllByTvId(itemId);

        Map<Long, List<TVStatEntity>> marketTVStatElementsMap = new HashMap<>();
        Map<Long, Long> marketTVStatPriceMap = new HashMap<>();
        Map<Long, Boolean> marketPriceInfo = new HashMap<>();
        Map<Long, TVStatEntity> tvStatEntityMap = new HashMap<>();
        tvMarketEntityList.forEach(el -> {
            List<TVStatEntity> entities = tvStatRepo.findByInterval(el.getId());
            if (!entities.isEmpty()) {
                tvStatEntityMap.put(el.getId(), entities.get(0));
                marketTVStatElementsMap.put(el.getId(), entities);
                marketTVStatPriceMap.put(el.getId(), entities.get(0).getPrice());
            }
        });

        marketTVStatElementsMap.keySet().forEach(el -> {
            Long priceSum = marketTVStatElementsMap.get(el).stream().map(TVStatEntity::getPrice).mapToLong(Long::longValue).sum();
            Long middlePrice = priceSum / marketTVStatElementsMap.get(el).size();
            Long currentSum = marketTVStatElementsMap.get(el).get(0).getPrice();
            Boolean isUp = null;

            if (!middlePrice.equals(currentSum)) {
                if (currentSum < middlePrice) {
                    isUp = true;
                } else {
                    isUp = false;
                }
            }
            marketPriceInfo.put(el, isUp);
        });

        if (!marketTVStatPriceMap.values().isEmpty()) {
            Long middlePrice = marketTVStatPriceMap.values().stream().mapToLong(Long::longValue).sum() / marketTVStatPriceMap.values().size();
            Long maxPrice = marketTVStatPriceMap.values().stream().mapToLong(Long::longValue).max().getAsLong();
            Long minPrice = marketTVStatPriceMap.values().stream().mapToLong(Long::longValue).min().getAsLong();

            marketTVStatPriceMap.keySet().forEach(el -> {
                String status = "Цена опустится";
                Long price = marketTVStatPriceMap.get(el);
                double rating = 1;

                if (price < middlePrice) {
                    rating = rating * coefficientMiddleBuff;
                } else if (price > middlePrice) {
                    rating = rating * coefficientMiddleDebuff;
                }

                if (marketPriceInfo.get(el)) {
                    rating = rating * coefficientPriceInfoDebuff;
                } else if (!marketPriceInfo.get(el)) {
                    rating = rating * coefficientPriceInfoBuff;
                }

                if (price >= minPrice && price < minPrice * percentDown) {
                    rating = rating * coefficientCorrectBuff;
                } else if (price > maxPrice * percentUp && price <= maxPrice) {
                    rating = rating * coefficientCorrectDebuff;
                }

                if (rating < percentRating) {
                    status = "Цена поднимется";
                }
                TVMarketEntity tvMarketEntity = tvStatEntityMap.get(el).getTvMarket();
                response.add(UniversalBuilder.buildPredicateDto(status,
                        tvMarketEntity.getMarket().getName(),
                        price,
                        tvMarketEntity.getUrl()));
            });
        }

        return response;
    }
}
