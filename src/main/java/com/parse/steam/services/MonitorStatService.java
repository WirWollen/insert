package com.parse.steam.services;

import com.parse.steam.converters.MarketConverter;
import com.parse.steam.dtos.stat.*;
import com.parse.steam.repo.MarketRepo;
import com.parse.steam.repo.MonitorMarketRepo;
import com.parse.steam.repo.MonitorStatRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MonitorStatService {
    private final MonitorStatRepo monitorStatRepo;
    private final MonitorMarketRepo monitorMarketRepo;
    private final MarketRepo marketRepo;

    public PriceTimeDto findStatByMonitorId(Long itemId, Long marketId) {
        var market = marketRepo.findById(marketId).get();
        return new PriceTimeDto(market.getName(),
                monitorStatRepo.findAllByItemIdAndId(itemId, market.getId()).stream().map(
                        el -> new TimePriceDto(el.getMoment(), el.getPrice())).toList());
    }

    public List<PriceTimeDto> findAllStatByMonitorId(Long itemId) {
        return marketRepo.findAll().stream().map(el2 -> new PriceTimeDto(el2.getName(),
                monitorStatRepo.findAllByItemIdAndId(itemId, el2.getId()).stream().map(
                        el -> new TimePriceDto(el.getMoment(), el.getPrice())).toList())).toList();
    }

    public List<StatCountMonitorMarketDto> countMonitorMarket() {
        return marketRepo.findAll().stream().map(MarketConverter::toDto)
                .toList().stream()
                .map(el -> new StatCountMonitorMarketDto(el.getName(), monitorMarketRepo.countByMarketId(el.getId()))).toList();
    }

    public List<StatCountMonitorMarketDto> countMonitorMarketInRedis() {
        return marketRepo.findAll().stream().map(MarketConverter::toDto)
                .toList().stream()
                .map(el -> new StatCountMonitorMarketDto(el.getName(), monitorMarketRepo.countInRedisByMarketId(el.getId()))).toList();
    }

    public List<StatLowestPriceDto> findCurrentPrices(Long itemId) {
        return marketRepo.findAll().stream().map(MarketConverter::toDto)
                .toList().stream()
                .map(el -> {
                    StatLowestPriceDto dto = new StatLowestPriceDto();
                    dto.setMarketName(el.getName());
                    Long price = 0L;
                    var entity = monitorStatRepo.findLastPrice(el.getId(), itemId);
                    if (entity != null) {
                        price = entity.getPrice();
                        dto.setUrl(entity.getMonitorMarketEntity().getUrl());
                    }
                    dto.setPrice(price);
                    return dto;
                }).toList();
    }

    public List<StatLowestPriceDto> findLowestPrices(Long itemId) {
        return marketRepo.findAll().stream().map(MarketConverter::toDto)
                .toList().stream()
                .map(el -> {
                    StatLowestPriceDto dto = new StatLowestPriceDto();
                    dto.setMarketName(el.getName());
                    Long price = 0L;
                    var entity = monitorStatRepo.findLowestPrice(el.getId(), itemId);
                    if (entity != null) price = entity.getPrice();
                    dto.setPrice(price);
                    dto.setUrl(el.getUrl());
                    return dto;
                }).toList();
    }
}
