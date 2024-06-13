package com.parse.steam.converters;

import com.parse.steam.dtos.WashingMachineStatDto;
import com.parse.steam.entities.WashingMachineStatEntity;
import org.springframework.beans.BeanUtils;

public class WashingMachineStatConverter {
    public static WashingMachineStatEntity toEntity(WashingMachineStatDto dto) {
        WashingMachineStatEntity entity = new WashingMachineStatEntity();
        BeanUtils.copyProperties(dto, entity);
        if (dto.getWashingMachineMarket() != null) entity.setWashingMachineMarket(WashingMachineMarketConverter.toEntity(dto.getWashingMachineMarket()));
        return entity;
    }

    public static WashingMachineStatDto toDto(WashingMachineStatEntity entity) {
        WashingMachineStatDto dto = new WashingMachineStatDto();
        BeanUtils.copyProperties(entity, dto);
        if (entity.getWashingMachineMarket() != null) dto.setWashingMachineMarket(WashingMachineMarketConverter.toDto(entity.getWashingMachineMarket()));
        return dto;
    }
}
