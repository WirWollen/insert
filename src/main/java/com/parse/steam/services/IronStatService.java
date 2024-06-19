package com.parse.steam.services;

import com.parse.steam.converters.MarketConverter;
import com.parse.steam.dtos.stat.PriceTimeDto;
import com.parse.steam.dtos.stat.StatCountMonitorMarketDto;
import com.parse.steam.dtos.stat.StatLowestPriceDto;
import com.parse.steam.dtos.stat.TimePriceDto;
import com.parse.steam.repo.IronMarketRepo;
import com.parse.steam.repo.IronStatRepo;
import com.parse.steam.repo.MarketRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IronStatService {
    private final IronStatRepo ironStatRepo;
    private final IronMarketRepo ironMarketRepo;
    private final MarketRepo marketRepo;

    public PriceTimeDto findStatByIronId(Long itemId, Long marketId) {
        var market = marketRepo.findById(marketId).get();
        return new PriceTimeDto(market.getName(),
                ironStatRepo.findAllByItemIdAndId(itemId, market.getId()).stream().map(
                        el -> new TimePriceDto(el.getMoment(), el.getPrice())).toList());
    }

    public List<PriceTimeDto> findAllStatByIronId(Long itemId) {
        return marketRepo.findAll().stream().map(el2 -> new PriceTimeDto(el2.getName(),
                ironStatRepo.findAllByItemIdAndId(itemId, el2.getId()).stream().map(
                        el -> new TimePriceDto(el.getMoment(), el.getPrice())).toList())).toList();
    }

    public List<StatCountMonitorMarketDto> countIronMarket() {
        return marketRepo.findAll().stream().map(MarketConverter::toDto)
                .toList().stream()
                .map(el -> new StatCountMonitorMarketDto(el.getName(), ironMarketRepo.countByMarketId(el.getId()))).toList();
    }

    public List<StatCountMonitorMarketDto> countIronMarketInRedis() {
        return marketRepo.findAll().stream().map(MarketConverter::toDto)
                .toList().stream()
                .map(el -> new StatCountMonitorMarketDto(el.getName(), ironMarketRepo.countInRedisByMarketId(el.getId()))).toList();
    }

    public List<StatLowestPriceDto> findCurrentPrices(Long itemId) {
        return marketRepo.findAll().stream().map(MarketConverter::toDto)
                .toList().stream()
                .map(el -> {
                    StatLowestPriceDto dto = new StatLowestPriceDto();
                    dto.setMarketName(el.getName());
                    Long price = 0L;
                    var entity = ironStatRepo.findLastPrice(el.getId(), itemId);
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
                    var entity = ironStatRepo.findLowestPrice(el.getId(), itemId);
                    if (entity != null) {
                        price = entity.getPrice();
                        dto.setUrl(entity.getIronMarketEntity().getUrl());
                    }
                    dto.setPrice(price);
                    return dto;
                }).toList();
    }
}
