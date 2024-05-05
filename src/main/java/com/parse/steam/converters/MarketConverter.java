package com.parse.steam.converters;

import com.parse.steam.dtos.MarketDto;
import com.parse.steam.entities.MarketEntity;
import org.springframework.beans.BeanUtils;

public class MarketConverter {
    public static MarketEntity toEntity(MarketDto dto) {
        MarketEntity entity = new MarketEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    public static MarketDto toDto(MarketEntity entity) {
        MarketDto dto = new MarketDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}
