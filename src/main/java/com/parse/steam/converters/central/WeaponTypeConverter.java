package com.parse.steam.converters.central;

import com.parse.steam.dtos.central.WeaponTypeDto;
import com.parse.steam.entities.central.WeaponTypeEntity;
import org.springframework.beans.BeanUtils;

public class WeaponTypeConverter {
    public static WeaponTypeDto toDto(WeaponTypeEntity entity) {
        WeaponTypeDto dto = new WeaponTypeDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static WeaponTypeEntity toEntity(WeaponTypeDto dto) {
        WeaponTypeEntity entity = new WeaponTypeEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
