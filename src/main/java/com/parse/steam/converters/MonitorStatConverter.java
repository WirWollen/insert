package com.parse.steam.converters;

import com.parse.steam.dtos.MonitorStatDto;
import com.parse.steam.entities.MonitorStatEntity;
import org.springframework.beans.BeanUtils;

public class MonitorStatConverter {
    public static MonitorStatEntity toEntity(MonitorStatDto dto) {
        MonitorStatEntity entity = new MonitorStatEntity();
        BeanUtils.copyProperties(dto, entity);
        if (dto.getMonitorMarketDto() != null) entity.setMonitorMarketEntity(MonitorMarketConverter.toEntity(dto.getMonitorMarketDto()));
        return entity;
    }

    public static MonitorStatDto toDto(MonitorStatEntity entity) {
        MonitorStatDto dto = new MonitorStatDto();
        BeanUtils.copyProperties(entity, dto);
        if (entity.getMonitorMarketEntity() != null) dto.setMonitorMarketDto(MonitorMarketConverter.toDto(entity.getMonitorMarketEntity()));
        return dto;
    }
}
