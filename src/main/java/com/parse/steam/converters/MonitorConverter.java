package com.parse.steam.converters;

import com.parse.steam.dtos.MonitorDto;
import com.parse.steam.entities.MonitorEntity;
import org.springframework.beans.BeanUtils;

public class MonitorConverter {
    public static MonitorEntity toEntity(MonitorDto dto) {
        MonitorEntity entity = new MonitorEntity();
        BeanUtils.copyProperties(dto, entity);
        if (dto.getBrand() != null) entity.setBrand(BrandConverter.toEntity(dto.getBrand()));
        if (dto.getSize() != null) entity.setSize(SizeConverter.toEntity(dto.getSize()));
        if (dto.getMatrix() != null) entity.setMatrix(MatrixConverter.toEntity(dto.getMatrix()));
        return entity;
    }

    public static MonitorDto toDto(MonitorEntity entity) {
        MonitorDto dto = new MonitorDto();
        BeanUtils.copyProperties(entity, dto);
        if (entity.getBrand() != null) dto.setBrand(BrandConverter.toDto(entity.getBrand()));
        if (entity.getSize() != null) dto.setSize(SizeConverter.toDto(entity.getSize()));
        if (entity.getMatrix() != null) dto.setMatrix(MatrixConverter.toDto(entity.getMatrix()));
        return dto;
    }
}
