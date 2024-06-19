package com.parse.steam.services;

import com.parse.steam.converters.MonitorConverter;
import com.parse.steam.dtos.MonitorDto;
import com.parse.steam.dtos.stat.PredicateDto;
import com.parse.steam.entities.MonitorEntity;
import com.parse.steam.entities.MonitorMarketEntity;
import com.parse.steam.entities.MonitorStatEntity;
import com.parse.steam.exceptions.ElementNotFoundException;
import com.parse.steam.repo.MonitorMarketRepo;
import com.parse.steam.repo.MonitorRepo;
import com.parse.steam.repo.MonitorStatRepo;
import com.parse.steam.utils.builders.MonitorBuilder;
import com.parse.steam.utils.builders.UniversalBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MonitorService {
    private final MonitorRepo monitorRepo;
    private final MonitorStatRepo monitorStatRepo;
    private final MonitorMarketRepo monitorMarketRepo;

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

    public MonitorDto saveMonitor(MonitorDto dto) {
        return MonitorConverter.toDto(monitorRepo.save(MonitorConverter.toEntity(dto)));
    }

    public List<MonitorDto> saveMonitorList(List<MonitorDto> dtoList) {
        return monitorRepo.saveAll(dtoList.stream().map(MonitorConverter::toEntity).toList()).stream().map(MonitorConverter::toDto).toList();
    }

    public MonitorDto archiveMonitor(Long id) throws ElementNotFoundException {
        MonitorEntity entity = monitorRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("monitor not found"));
        entity.setArchived(true);
        return MonitorConverter.toDto(monitorRepo.save(entity));
    }

    public MonitorDto unarchiveMonitor(Long id) throws ElementNotFoundException {
        MonitorEntity entity = monitorRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("monitor not found"));
        entity.setArchived(false);
        return MonitorConverter.toDto(monitorRepo.save(entity));
    }

    public MonitorDto updateMonitor(MonitorDto dto) throws ElementNotFoundException {
        MonitorEntity entity = monitorRepo.findById(dto.getId()).orElseThrow(() -> new ElementNotFoundException("monitor not found"));
        MonitorEntity updatedEntity = MonitorBuilder.buildUpdatedMonitorEntity(entity, MonitorConverter.toEntity(dto));
        updatedEntity = monitorRepo.save(updatedEntity);
        return MonitorConverter.toDto(updatedEntity);
    }

    public MonitorDto getMonitorById(Long id) throws ElementNotFoundException {
        return MonitorConverter.toDto(monitorRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("monitor not found")));
    }

    public List<MonitorDto> getAllMonitor() {
        return monitorRepo.findAll().stream().map(MonitorConverter::toDto).toList();
    }

    public List<PredicateDto> predicateUpPrice(Long itemId) {
        MonitorEntity monitorEntity = monitorRepo.findById(itemId).orElse(null);

        if (monitorEntity == null || monitorEntity.getArchived()) {
            return new ArrayList<>();
        }

        List<PredicateDto> response = new ArrayList<>();
        List<MonitorMarketEntity> monitorMarketEntityList = monitorMarketRepo.findAllByMonitorId(itemId);

        Map<Long, List<MonitorStatEntity>> marketMonitorStatElementsMap = new HashMap<>();
        Map<Long, Long> marketMonitorStatPriceMap = new HashMap<>();
        Map<Long, Boolean> marketPriceInfo = new HashMap<>();
        Map<Long, MonitorStatEntity> monitorStatEntityMap = new HashMap<>();
        monitorMarketEntityList.forEach(el -> {
            List<MonitorStatEntity> entities = monitorStatRepo.findByInterval(el.getId());
            if (!entities.isEmpty()) {
                monitorStatEntityMap.put(el.getId(), entities.get(0));
                marketMonitorStatElementsMap.put(el.getId(), entities);
                marketMonitorStatPriceMap.put(el.getId(), entities.get(0).getPrice());
            }
        });

        marketMonitorStatElementsMap.keySet().forEach(el -> {
            Long priceSum = marketMonitorStatElementsMap.get(el).stream().map(MonitorStatEntity::getPrice).mapToLong(Long::longValue).sum();
            Long middlePrice = priceSum / marketMonitorStatElementsMap.get(el).size();
            Long currentSum = marketMonitorStatElementsMap.get(el).get(0).getPrice();
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

        if (!marketMonitorStatPriceMap.values().isEmpty()) {
            Long middlePrice = marketMonitorStatPriceMap.values().stream().mapToLong(Long::longValue).sum() / marketMonitorStatPriceMap.values().size();
            Long maxPrice = marketMonitorStatPriceMap.values().stream().mapToLong(Long::longValue).max().getAsLong();
            Long minPrice = marketMonitorStatPriceMap.values().stream().mapToLong(Long::longValue).min().getAsLong();

            marketMonitorStatPriceMap.keySet().forEach(el -> {
                String status = "Цена опустится";
                Long price = marketMonitorStatPriceMap.get(el);
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
                MonitorMarketEntity monitorMarketEntity = monitorStatEntityMap.get(el).getMonitorMarketEntity();
                response.add(UniversalBuilder.buildPredicateDto(status, monitorMarketEntity.getMarketEntity().getName()));
            });
        }

        return response;
    }
}
