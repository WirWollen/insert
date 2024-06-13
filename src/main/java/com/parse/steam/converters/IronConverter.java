package com.parse.steam.converters;

import com.parse.steam.dtos.IronDto;
import com.parse.steam.entities.IronEntity;
import org.springframework.beans.BeanUtils;

public class IronConverter {
    public static IronEntity toEntity(IronDto dto) {
        IronEntity entity = new IronEntity();
        BeanUtils.copyProperties(dto, entity);
        if (dto.getBrand() != null) entity.setBrand(BrandConverter.toEntity(dto.getBrand()));
        if (dto.getIronSole() != null) entity.setIronSole(IronSoleConverter.toEntity(dto.getIronSole()));
        return entity;
    }

    public static IronDto toDto(IronEntity entity) {
        IronDto dto = new IronDto();
        BeanUtils.copyProperties(entity, dto);
        if (entity.getBrand() != null) dto.setBrand(BrandConverter.toDto(entity.getBrand()));
        if (entity.getIronSole() != null) dto.setIronSole(IronSoleConverter.toDto(entity.getIronSole()));
        return dto;
    }
}
