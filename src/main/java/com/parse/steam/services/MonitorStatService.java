package com.parse.steam.services;

import com.parse.steam.converters.MarketConverter;
import com.parse.steam.converters.MonitorStatConverter;
import com.parse.steam.dtos.stat.StatCountMonitorMarketDto;
import com.parse.steam.dtos.stat.StatElDto;
import com.parse.steam.dtos.stat.StatLowestPriceDto;
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

    public List<StatElDto> findAllStatByMonitorId(Long itemId) {
        return marketRepo.findAll().stream().map(MarketConverter::toDto)
                .toList().stream()
                .map(el -> new StatElDto(el.getName(), monitorStatRepo.findAllByItemIdAndId(itemId, el.getId())
                        .stream().map(MonitorStatConverter::toDto).toList())).toList();
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
                    if (monitorStatRepo.findLastPrice(el.getId(), itemId) != null) price = dto.getPrice();
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
                    if (monitorStatRepo.findLowestPrice(el.getId(), itemId) != null) price = dto.getPrice();
                    dto.setPrice(price);
                    dto.setUrl(el.getUrl());
                    return dto;
                }).toList();
    }
}
