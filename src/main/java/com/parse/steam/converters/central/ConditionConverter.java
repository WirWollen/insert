package com.parse.steam.converters.central;

import com.parse.steam.dtos.central.ConditionDto;
import com.parse.steam.entities.central.ConditionEntity;
import org.springframework.beans.BeanUtils;

public class ConditionConverter {
    public static ConditionDto toDto(ConditionEntity entity) {
        ConditionDto dto = new ConditionDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static ConditionEntity toEntity(ConditionDto dto) {
        ConditionEntity entity = new ConditionEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
