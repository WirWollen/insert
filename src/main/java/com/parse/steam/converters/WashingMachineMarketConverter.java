package com.parse.steam.converters;

import com.parse.steam.dtos.WashingMachineMarketDto;
import com.parse.steam.entities.WashingMachineMarketEntity;
import org.springframework.beans.BeanUtils;

public class WashingMachineMarketConverter {
    public static WashingMachineMarketEntity toEntity(WashingMachineMarketDto dto) {
        WashingMachineMarketEntity entity = new WashingMachineMarketEntity();
        BeanUtils.copyProperties(dto, entity);
        if (dto.getMarket() != null) entity.setMarketEntity(MarketConverter.toEntity(dto.getMarket()));
        if (dto.getWashingMachine() != null) entity.setWashingMachine(WashingMachineConverter.toEntity(dto.getWashingMachine()));
        return entity;
    }

    public static WashingMachineMarketDto toDto(WashingMachineMarketEntity entity) {
        WashingMachineMarketDto dto = new WashingMachineMarketDto();
        BeanUtils.copyProperties(entity, dto);
        if (entity.getMarketEntity() != null) dto.setMarket(MarketConverter.toDto(entity.getMarketEntity()));
        if (entity.getWashingMachine() != null) dto.setWashingMachine(WashingMachineConverter.toDto(entity.getWashingMachine()));
        return dto;
    }
}
