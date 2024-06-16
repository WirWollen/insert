package com.parse.steam.services;

import com.parse.steam.converters.MarketConverter;
import com.parse.steam.dtos.stat.PriceTimeDto;
import com.parse.steam.dtos.stat.StatCountMonitorMarketDto;
import com.parse.steam.dtos.stat.StatLowestPriceDto;
import com.parse.steam.dtos.stat.TimePriceDto;
import com.parse.steam.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WashingMachineStatService {
    private final WashingMachineStatRepo washingMachineStatRepo;
    private final WashingMachineMarketRepo washingMachineMarketRepo;
    private final MarketRepo marketRepo;

    public PriceTimeDto findStatByMonitorId(Long itemId, Long marketId) {
        var market = marketRepo.findById(marketId).get();
        return new PriceTimeDto(market.getName(),
                washingMachineStatRepo.findAllByItemIdAndId(itemId, market.getId()).stream().map(
                        el -> new TimePriceDto(el.getMoment(), el.getPrice())).toList());
    }

    public List<PriceTimeDto> findAllStatByMonitorId(Long itemId) {
        return marketRepo.findAll().stream().map(el2 -> new PriceTimeDto(el2.getName(),
                washingMachineStatRepo.findAllByItemIdAndId(itemId, el2.getId()).stream().map(
                        el -> new TimePriceDto(el.getMoment(), el.getPrice())).toList())).toList();
    }

    public List<StatCountMonitorMarketDto> countMonitorMarket() {
        return marketRepo.findAll().stream().map(MarketConverter::toDto)
                .toList().stream()
                .map(el -> new StatCountMonitorMarketDto(el.getName(), washingMachineMarketRepo.countByMarketId(el.getId()))).toList();
    }

    public List<StatCountMonitorMarketDto> countMonitorMarketInRedis() {
        return marketRepo.findAll().stream().map(MarketConverter::toDto)
                .toList().stream()
                .map(el -> new StatCountMonitorMarketDto(el.getName(), washingMachineMarketRepo.countInRedisByMarketId(el.getId()))).toList();
    }

    public List<StatLowestPriceDto> findCurrentPrices(Long itemId) {
        return marketRepo.findAll().stream().map(MarketConverter::toDto)
                .toList().stream()
                .map(el -> {
                    StatLowestPriceDto dto = new StatLowestPriceDto();
                    dto.setMarketName(el.getName());
                    Long price = 0L;
                    var entity = washingMachineStatRepo.findLastPrice(el.getId(), itemId);
                    if (entity != null) price = entity.getPrice();
                    dto.setPrice(price);
                    dto.setUrl(el.getUrl());
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
                    var entity = washingMachineStatRepo.findLowestPrice(el.getId(), itemId);
                    if (entity != null) price = entity.getPrice();
                    dto.setPrice(price);
                    dto.setUrl(el.getUrl());
                    return dto;
                }).toList();
    }
}
