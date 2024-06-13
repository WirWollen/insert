package com.parse.steam.converters;

import com.parse.steam.dtos.OsDto;
import com.parse.steam.entities.OsEntity;
import org.springframework.beans.BeanUtils;

public class OsConverter {
    public static OsDto toDto(OsEntity entity) {
        OsDto dto = new OsDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static OsEntity toEntity(OsDto dto) {
        OsEntity entity = new OsEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
