package com.parse.steam.converters;

import com.parse.steam.dtos.WashingMachineDto;
import com.parse.steam.entities.WashingMachineEntity;
import org.springframework.beans.BeanUtils;

public class WashingMachineConverter {
    public static WashingMachineEntity toEntity(WashingMachineDto dto) {
        WashingMachineEntity entity = new WashingMachineEntity();
        BeanUtils.copyProperties(dto, entity);
        if (dto.getBrand() != null) entity.setBrand(BrandConverter.toEntity(dto.getBrand()));
        if (dto.getWashTech() != null) entity.setWashTech(WashTechConverter.toEntity(dto.getWashTech()));
        return entity;
    }

    public static WashingMachineDto toDto(WashingMachineEntity entity) {
        WashingMachineDto dto = new WashingMachineDto();
        BeanUtils.copyProperties(entity, dto);
        if (entity.getBrand() != null) dto.setBrand(BrandConverter.toDto(entity.getBrand()));
        if (entity.getWashTech() != null) dto.setWashTech(WashTechConverter.toDto(entity.getWashTech()));
        return dto;
    }
}
