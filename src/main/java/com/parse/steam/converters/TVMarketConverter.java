package com.parse.steam.converters;

import com.parse.steam.dtos.TVMarketDto;
import com.parse.steam.entities.TVMarketEntity;
import org.springframework.beans.BeanUtils;

public class TVMarketConverter {
    public static TVMarketEntity toEntity(TVMarketDto dto) {
        TVMarketEntity entity = new TVMarketEntity();
        BeanUtils.copyProperties(dto, entity);
        if (dto.getMarket() != null) entity.setMarket(MarketConverter.toEntity(dto.getMarket()));
        if (dto.getTv() != null) entity.setTv(TVConverter.toEntity(dto.getTv()));
        return entity;
    }

    public static TVMarketDto toDto(TVMarketEntity entity) {
        TVMarketDto dto = new TVMarketDto();
        BeanUtils.copyProperties(entity, dto);
        if (entity.getMarket() != null) dto.setMarket(MarketConverter.toDto(entity.getMarket()));
        if (entity.getTv() != null) dto.setTv(TVConverter.toDto(entity.getTv()));
        return dto;
    }
}
