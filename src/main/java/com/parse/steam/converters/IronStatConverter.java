package com.parse.steam.converters;

import com.parse.steam.dtos.IronStatDto;
import com.parse.steam.entities.IronStatEntity;
import org.springframework.beans.BeanUtils;

public class IronStatConverter {
    public static IronStatEntity toEntity(IronStatDto dto) {
        IronStatEntity entity = new IronStatEntity();
        BeanUtils.copyProperties(dto, entity);
        if (dto.getIronMarket() != null) entity.setIronMarketEntity(IronMarketConverter.toEntity(dto.getIronMarket()));
        return entity;
    }

    public static IronStatDto toDto(IronStatEntity entity) {
        IronStatDto dto = new IronStatDto();
        BeanUtils.copyProperties(entity, dto);
        if (entity.getIronMarketEntity() != null) dto.setIronMarket(IronMarketConverter.toDto(entity.getIronMarketEntity()));
        return dto;
    }
}
