package com.parse.steam.converters;

import com.parse.steam.dtos.SizeDto;
import com.parse.steam.entities.MonitorSizeEntity;
import org.springframework.beans.BeanUtils;

public class SizeConverter {
    public static SizeDto toDto(MonitorSizeEntity entity) {
        SizeDto dto = new SizeDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static MonitorSizeEntity toEntity(SizeDto dto) {
        MonitorSizeEntity entity = new MonitorSizeEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
