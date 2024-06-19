package com.parse.steam.utils.builders;

import com.parse.steam.dtos.stat.PredicateDto;

public class UniversalBuilder {

    public static PredicateDto buildPredicateDto(String status, String marketName) {
        PredicateDto dto = new PredicateDto();
        dto.setStatus(status);
        dto.setMarketName(marketName);
        return dto;
    }
}
