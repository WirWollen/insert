package com.parse.steam.converters;

import com.parse.steam.dtos.IronSoleDto;
import com.parse.steam.entities.IronSoleEntity;
import org.springframework.beans.BeanUtils;

public class IronSoleConverter {
    public static IronSoleDto toDto(IronSoleEntity entity) {
        IronSoleDto dto = new IronSoleDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static IronSoleEntity toEntity(IronSoleDto dto) {
        IronSoleEntity entity = new IronSoleEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
