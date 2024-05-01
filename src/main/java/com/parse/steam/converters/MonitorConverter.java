package com.parse.steam.converters;

import com.parse.steam.dtos.MonitorDto;
import com.parse.steam.entities.MonitorEntity;
import org.springframework.beans.BeanUtils;

public class MonitorConverter {
    public static MonitorEntity toEntity(MonitorDto dto) {
        MonitorEntity entity = new MonitorEntity();
        BeanUtils.copyProperties(dto, entity);
        entity.setBrand(BrandConverter.toEntity(dto.getBrand()));
        entity.setSize(SizeConverter.toEntity(dto.getSize()));
        entity.setMatrix(MatrixConverter.toEntity(dto.getMatrix()));
        return entity;
    }

    public static MonitorDto toDto(MonitorEntity entity) {
        MonitorDto dto = new MonitorDto();
        BeanUtils.copyProperties(entity, dto);
        dto.setBrand(BrandConverter.toDto(entity.getBrand()));
        dto.setSize(SizeConverter.toDto(entity.getSize()));
        dto.setMatrix(MatrixConverter.toDto(entity.getMatrix()));
        return dto;
    }
}
