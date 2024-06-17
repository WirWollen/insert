package com.parse.steam.services;

import com.parse.steam.dtos.stat.PredicateDto;
import com.parse.steam.dtos.stat.StatLowestPriceDto;

public class UniversalBuilder {

    public static PredicateDto buildPredicateDto(String status, String marketName, Long price, String url) {
        PredicateDto dto = new PredicateDto();
        StatLowestPriceDto stat = new StatLowestPriceDto();
        stat.setUrl(url);
        stat.setMarketName(marketName);
        stat.setPrice(price);
        dto.setStatus(status);
        dto.setStatLowestPriceDto(stat);
        return dto;
    }
}
