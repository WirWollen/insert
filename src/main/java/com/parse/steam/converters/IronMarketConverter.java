package com.parse.steam.converters;

import com.parse.steam.dtos.IronMarketDto;
import com.parse.steam.entities.IronMarketEntity;
import org.springframework.beans.BeanUtils;

public class IronMarketConverter {
    public static IronMarketEntity toEntity(IronMarketDto dto) {
        IronMarketEntity entity = new IronMarketEntity();
        BeanUtils.copyProperties(dto, entity);
        if (dto.getMarket() != null) entity.setMarket(MarketConverter.toEntity(dto.getMarket()));
        if (dto.getIron() != null) entity.setIron(IronConverter.toEntity(dto.getIron()));
        return entity;
    }

    public static IronMarketDto toDto(IronMarketEntity entity) {
        IronMarketDto dto = new IronMarketDto();
        BeanUtils.copyProperties(entity, dto);
        if (entity.getMarket() != null) dto.setMarket(MarketConverter.toDto(entity.getMarket()));
        if (entity.getIron() != null) dto.setIron(IronConverter.toDto(entity.getIron()));
        return dto;
    }
}
