package com.parse.steam.converters;

import com.parse.steam.dtos.ScreenTechDto;
import com.parse.steam.entities.ScreenTechEntity;
import org.springframework.beans.BeanUtils;

public class ScreenTechConverter {
    public static ScreenTechDto toDto(ScreenTechEntity entity) {
        ScreenTechDto dto = new ScreenTechDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static ScreenTechEntity toEntity(ScreenTechDto dto) {
        ScreenTechEntity entity = new ScreenTechEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
