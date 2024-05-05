package com.parse.steam.converters;

import com.parse.steam.dtos.SizeDto;
import com.parse.steam.entities.SizeEntity;
import org.springframework.beans.BeanUtils;

public class SizeConverter {
    public static SizeDto toDto(SizeEntity entity) {
        SizeDto dto = new SizeDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static SizeEntity toEntity(SizeDto dto) {
        SizeEntity entity = new SizeEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
