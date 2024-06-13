package com.parse.steam.converters;

import com.parse.steam.dtos.WashTechDto;
import com.parse.steam.entities.WashTechEntity;
import org.springframework.beans.BeanUtils;

public class WashTechConverter {
    public static WashTechDto toDto(WashTechEntity entity) {
        WashTechDto dto = new WashTechDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static WashTechEntity toEntity(WashTechDto dto) {
        WashTechEntity entity = new WashTechEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
