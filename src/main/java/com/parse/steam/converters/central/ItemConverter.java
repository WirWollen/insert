package com.parse.steam.converters.central;

import com.parse.steam.dtos.central.ItemDto;
import com.parse.steam.entities.central.ItemEntity;
import org.springframework.beans.BeanUtils;

public class ItemConverter {
    public static ItemDto toDto(ItemEntity entity) {
        ItemDto dto = new ItemDto();
        BeanUtils.copyProperties(entity, dto);
        dto.setConditionDto(ConditionConverter.toDto(entity.getCondition()));
        dto.setWeaponTypeDto(WeaponTypeConverter.toDto(entity.getWeaponType()));
        dto.setNamingDto(NamingConverter.toDto(entity.getNaming()));
        return dto;
    }

    public static ItemEntity toEntity(ItemDto dto) {
        ItemEntity entity = new ItemEntity();
        BeanUtils.copyProperties(dto, entity);
        entity.setCondition(ConditionConverter.toEntity(dto.getConditionDto()));
        entity.setWeaponType(WeaponTypeConverter.toEntity(dto.getWeaponTypeDto()));
        entity.setNaming(NamingConverter.toEntity(dto.getNamingDto()));
        return entity;
    }
}
