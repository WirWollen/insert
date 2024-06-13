package com.parse.steam.converters;

import com.parse.steam.dtos.TVDto;
import com.parse.steam.entities.TVEntity;
import org.springframework.beans.BeanUtils;

public class TVConverter {
    public static TVEntity toEntity(TVDto dto) {
        TVEntity entity = new TVEntity();
        BeanUtils.copyProperties(dto, entity);
        if (dto.getBrand() != null) entity.setBrand(BrandConverter.toEntity(dto.getBrand()));
        if (dto.getSize() != null) entity.setSize(SizeConverter.toEntity(dto.getSize()));
        if (dto.getMatrix() != null) entity.setMatrix(MatrixConverter.toEntity(dto.getMatrix()));
        if (dto.getOs() != null) entity.setOs(OsConverter.toEntity(dto.getOs()));
        if (dto.getScreenTech() != null) entity.setScreenTech(ScreenTechConverter.toEntity(dto.getScreenTech()));
        return entity;
    }

    public static TVDto toDto(TVEntity entity) {
        TVDto dto = new TVDto();
        BeanUtils.copyProperties(entity, dto);
        if (entity.getBrand() != null) dto.setBrand(BrandConverter.toDto(entity.getBrand()));
        if (entity.getSize() != null) dto.setSize(SizeConverter.toDto(entity.getSize()));
        if (entity.getMatrix() != null) dto.setMatrix(MatrixConverter.toDto(entity.getMatrix()));
        if (entity.getOs() != null) dto.setOs(OsConverter.toDto(entity.getOs()));
        if (entity.getScreenTech() != null) dto.setScreenTech(ScreenTechConverter.toDto(entity.getScreenTech()));
        return dto;
    }
}
