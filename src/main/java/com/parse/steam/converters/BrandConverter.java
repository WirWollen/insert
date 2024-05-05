package com.parse.steam.converters;

import com.parse.steam.dtos.BrandDto;
import com.parse.steam.entities.BrandEntity;
import org.springframework.beans.BeanUtils;

public class BrandConverter {
    public static BrandDto toDto(BrandEntity entity) {
        BrandDto dto = new BrandDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static BrandEntity toEntity(BrandDto dto) {
        BrandEntity entity = new BrandEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
