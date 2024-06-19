package com.parse.steam.services;

import com.parse.steam.converters.WashingMachineConverter;
import com.parse.steam.dtos.WashingMachineDto;
import com.parse.steam.dtos.stat.PredicateDto;
import com.parse.steam.entities.*;
import com.parse.steam.exceptions.ElementNotFoundException;
import com.parse.steam.repo.WashingMachineMarketRepo;
import com.parse.steam.repo.WashingMachineRepo;
import com.parse.steam.repo.WashingMachineStatRepo;
import com.parse.steam.utils.builders.UniversalBuilder;
import com.parse.steam.utils.builders.WashingMachineBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class WashingMachineService {
    private final WashingMachineRepo washingMachineRepo;
    private final WashingMachineMarketRepo washingMachineMarketRepo;
    private final WashingMachineStatRepo washingMachineStatRepo;

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

    public WashingMachineDto saveWashingMachine(WashingMachineDto dto) {
        return WashingMachineConverter.toDto(washingMachineRepo.save(WashingMachineConverter.toEntity(dto)));
    }

    public List<WashingMachineDto> saveWashingMachineList(List<WashingMachineDto> dtoList) {
        return washingMachineRepo.saveAll(dtoList.stream().map(WashingMachineConverter::toEntity).toList()).stream().map(WashingMachineConverter::toDto).toList();
    }

    public WashingMachineDto archiveWashingMachine(Long id) throws ElementNotFoundException {
        WashingMachineEntity entity = washingMachineRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("washing machine not found"));
        entity.setArchived(true);
        return WashingMachineConverter.toDto(washingMachineRepo.save(entity));
    }

    public WashingMachineDto unarchiveWashingMachine(Long id) throws ElementNotFoundException {
        WashingMachineEntity entity = washingMachineRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("washing machine not found"));
        entity.setArchived(false);
        return WashingMachineConverter.toDto(washingMachineRepo.save(entity));
    }

    public WashingMachineDto updateWashingMachine(WashingMachineDto dto) throws ElementNotFoundException {
        WashingMachineEntity entity = washingMachineRepo.findById(dto.getId()).orElseThrow(() -> new ElementNotFoundException("washing machine not found"));
        WashingMachineEntity updatedEntity = WashingMachineBuilder.buildUpdatedWashingMachineEntity(entity, WashingMachineConverter.toEntity(dto));
        updatedEntity = washingMachineRepo.save(updatedEntity);
        return WashingMachineConverter.toDto(updatedEntity);
    }

    public WashingMachineDto getWashingMachineById(Long id) throws ElementNotFoundException {
        return WashingMachineConverter.toDto(washingMachineRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("washing machine not found")));
    }

    public List<WashingMachineDto> getAllWashingMachine() {
        return washingMachineRepo.findAll().stream().map(WashingMachineConverter::toDto).toList();
    }

    public List<PredicateDto> predicateUpPrice(Long itemId) {
        WashingMachineEntity washingMachineEntity = washingMachineRepo.findById(itemId).orElse(null);

        if (washingMachineEntity == null || washingMachineEntity.getArchived()) {
            return new ArrayList<>();
        }

        List<PredicateDto> response = new ArrayList<>();
        List<WashingMachineMarketEntity> washingMachineMarketEntityList = washingMachineMarketRepo.findAllByWashingMachineId(itemId);

        Map<Long, List<WashingMachineStatEntity>> marketWashingMachineStatElementsMap = new HashMap<>();
        Map<Long, Long> marketWashingMachineStatPriceMap = new HashMap<>();
        Map<Long, Boolean> marketPriceInfo = new HashMap<>();
        Map<Long, WashingMachineStatEntity> washingMachineStatEntityMap = new HashMap<>();
        washingMachineMarketEntityList.forEach(el -> {
            List<WashingMachineStatEntity> entities = washingMachineStatRepo.findByInterval(el.getId());
            if (!entities.isEmpty()) {
                washingMachineStatEntityMap.put(el.getId(), entities.get(0));
                marketWashingMachineStatElementsMap.put(el.getId(), entities);
                marketWashingMachineStatPriceMap.put(el.getId(), entities.get(0).getPrice());
            }
        });

        marketWashingMachineStatElementsMap.keySet().forEach(el -> {
            Long priceSum = marketWashingMachineStatElementsMap.get(el).stream().map(WashingMachineStatEntity::getPrice).mapToLong(Long::longValue).sum();
            Long middlePrice = priceSum / marketWashingMachineStatElementsMap.get(el).size();
            Long currentSum = marketWashingMachineStatElementsMap.get(el).get(0).getPrice();
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

        if (!marketWashingMachineStatPriceMap.values().isEmpty()) {
            Long middlePrice = marketWashingMachineStatPriceMap.values().stream().mapToLong(Long::longValue).sum() / marketWashingMachineStatPriceMap.values().size();
            Long maxPrice = marketWashingMachineStatPriceMap.values().stream().mapToLong(Long::longValue).max().getAsLong();
            Long minPrice = marketWashingMachineStatPriceMap.values().stream().mapToLong(Long::longValue).min().getAsLong();

            marketWashingMachineStatPriceMap.keySet().forEach(el -> {
                String status = "Цена опустится";
                Long price = marketWashingMachineStatPriceMap.get(el);
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
                WashingMachineMarketEntity washingMachineMarketEntity = washingMachineStatEntityMap.get(el).getWashingMachineMarket();
                response.add(UniversalBuilder.buildPredicateDto(status, washingMachineMarketEntity.getMarketEntity().getName()));
            });
        }

        return response;
    }
}
