package com.parse.steam.converters.central;

import com.parse.steam.dtos.central.NamingDto;
import com.parse.steam.entities.central.NamingEntity;
import org.springframework.beans.BeanUtils;

public class NamingConverter {
    public static NamingDto toDto(NamingEntity entity) {
        NamingDto dto = new NamingDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static NamingEntity toEntity(NamingDto dto) {
        NamingEntity entity = new NamingEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
