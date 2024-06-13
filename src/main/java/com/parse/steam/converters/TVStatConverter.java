package com.parse.steam.converters;

import com.parse.steam.dtos.TVStatDto;
import com.parse.steam.entities.TVStatEntity;
import org.springframework.beans.BeanUtils;

public class TVStatConverter {
    public static TVStatEntity toEntity(TVStatDto dto) {
        TVStatEntity entity = new TVStatEntity();
        BeanUtils.copyProperties(dto, entity);
        if (dto.getTvMarket() != null) entity.setTvMarket(TVMarketConverter.toEntity(dto.getTvMarket()));
        return entity;
    }

    public static TVStatDto toDto(TVStatEntity entity) {
        TVStatDto dto = new TVStatDto();
        BeanUtils.copyProperties(entity, dto);
        if (entity.getTvMarket() != null) dto.setTvMarket(TVMarketConverter.toDto(entity.getTvMarket()));
        return dto;
    }
}
