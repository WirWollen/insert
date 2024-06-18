package com.parse.steam.services;

import com.parse.steam.converters.IronConverter;
import com.parse.steam.dtos.IronDto;
import com.parse.steam.dtos.stat.PredicateDto;
import com.parse.steam.entities.*;
import com.parse.steam.exceptions.ElementNotFoundException;
import com.parse.steam.repo.IronMarketRepo;
import com.parse.steam.repo.IronRepo;
import com.parse.steam.repo.IronStatRepo;
import com.parse.steam.utils.builders.IronBuilder;
import com.parse.steam.utils.builders.MonitorBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class IronService {
    private final IronRepo ironRepo;
    private final IronMarketRepo ironMarketRepo;
    private final IronStatRepo ironStatRepo;

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

    public IronDto saveIron(IronDto dto) {
        return IronConverter.toDto(ironRepo.save(IronConverter.toEntity(dto)));
    }

    public List<IronDto> saveIronList(List<IronDto> dtoList) {
        return ironRepo.saveAll(dtoList.stream().map(IronConverter::toEntity).toList()).stream().map(IronConverter::toDto).toList();
    }

    public IronDto archiveIron(Long id) throws ElementNotFoundException {
        IronEntity entity = ironRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("iron not found"));
        entity.setArchived(true);
        return IronConverter.toDto(ironRepo.save(entity));
    }

    public IronDto unarchiveIron(Long id) throws ElementNotFoundException {
        IronEntity entity = ironRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("iron not found"));
        entity.setArchived(false);
        return IronConverter.toDto(ironRepo.save(entity));
    }

    public IronDto updateIron(IronDto dto) throws ElementNotFoundException {
        IronEntity entity = ironRepo.findById(dto.getId()).orElseThrow(() -> new ElementNotFoundException("iron not found"));
        IronEntity updatedEntity = IronBuilder.buildUpdatedIronEntity(entity, IronConverter.toEntity(dto));
        updatedEntity = ironRepo.save(updatedEntity);
        return IronConverter.toDto(updatedEntity);
    }

    public IronDto getIronById(Long id) throws ElementNotFoundException {
        return IronConverter.toDto(ironRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("iron not found")));
    }

    public List<IronDto> getAllIron() {
        return ironRepo.findAll().stream().map(IronConverter::toDto).toList();
    }

    public List<PredicateDto> predicateUpPrice(Long itemId) {
        IronEntity ironEntity = ironRepo.findById(itemId).orElse(null);

        if (ironEntity == null || ironEntity.getArchived()) {
            return new ArrayList<>();
        }

        List<PredicateDto> response = new ArrayList<>();
        List<IronMarketEntity> ironMarketEntityList = ironMarketRepo.findAllByIronId(itemId);

        Map<Long, List<IronStatEntity>> marketIronStatElementsMap = new HashMap<>();
        Map<Long, Long> marketIronStatPriceMap = new HashMap<>();
        Map<Long, Boolean> marketPriceInfo = new HashMap<>();
        Map<Long, IronStatEntity> ironStatEntityMap = new HashMap<>();
        ironMarketEntityList.forEach(el -> {
            List<IronStatEntity> entities = ironStatRepo.findByInterval(el.getId());
            if (!entities.isEmpty()) {
                ironStatEntityMap.put(el.getId(), entities.get(0));
                marketIronStatElementsMap.put(el.getId(), entities);
                marketIronStatPriceMap.put(el.getId(), entities.get(0).getPrice());
            }
        });

        marketIronStatElementsMap.keySet().forEach(el -> {
            Long priceSum = marketIronStatElementsMap.get(el).stream().map(IronStatEntity::getPrice).mapToLong(Long::longValue).sum();
            Long middlePrice = priceSum / marketIronStatElementsMap.get(el).size();
            Long currentSum = marketIronStatElementsMap.get(el).get(0).getPrice();
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

        if (!marketIronStatPriceMap.values().isEmpty()) {
            Long middlePrice = marketIronStatPriceMap.values().stream().mapToLong(Long::longValue).sum() / marketIronStatPriceMap.values().size();
            Long maxPrice = marketIronStatPriceMap.values().stream().mapToLong(Long::longValue).max().getAsLong();
            Long minPrice = marketIronStatPriceMap.values().stream().mapToLong(Long::longValue).min().getAsLong();

            marketIronStatPriceMap.keySet().forEach(el -> {
                String status = "Цена опустится";
                Long price = marketIronStatPriceMap.get(el);
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
                IronMarketEntity ironMarketEntity = ironStatEntityMap.get(el).getIronMarketEntity();
                response.add(UniversalBuilder.buildPredicateDto(status,
                        ironMarketEntity.getMarket().getName(),
                        price,
                        ironMarketEntity.getUrl()));
            });
        }

        return response;
    }

}
