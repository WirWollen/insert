package com.parse.steam.converters;

import com.parse.steam.dtos.MonitorMarketDto;
import com.parse.steam.entities.MonitorMarketEntity;
import org.springframework.beans.BeanUtils;

public class MonitorMarketConverter {
    public static MonitorMarketEntity toEntity(MonitorMarketDto dto) {
        MonitorMarketEntity entity = new MonitorMarketEntity();
        BeanUtils.copyProperties(dto, entity);
        entity.setMarketEntity(MarketConverter.toEntity(dto.getMarketDto()));
        entity.setMonitorEntity(MonitorConverter.toEntity(dto.getMonitorDto()));
        return entity;
    }

    public static MonitorMarketDto toDto(MonitorMarketEntity entity) {
        MonitorMarketDto dto = new MonitorMarketDto();
        BeanUtils.copyProperties(entity, dto);
        dto.setMarketDto(MarketConverter.toDto(entity.getMarketEntity()));
        dto.setMonitorDto(MonitorConverter.toDto(entity.getMonitorEntity()));
        return dto;
    }
}
