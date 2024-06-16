package com.parse.steam.services;

import com.parse.steam.converters.WashingMachineMarketConverter;
import com.parse.steam.dtos.WashingMachineMarketDto;
import com.parse.steam.dtos.redis.OuterDto;
import com.parse.steam.dtos.redis.ShopName;
import com.parse.steam.entities.WashingMachineMarketEntity;
import com.parse.steam.exceptions.ElementIsArchivedException;
import com.parse.steam.exceptions.ElementNotFoundException;
import com.parse.steam.repo.WashingMachineMarketRepo;
import com.parse.steam.repo.redis.RedisRepo;
import com.parse.steam.utils.builders.RedisBuilder;
import com.parse.steam.utils.builders.WashingMachineMarketBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WashingMachineMarketService {
    private final WashingMachineMarketRepo washingMachineMarketRepo;
    private final RedisRepo redisRepo;

    public WashingMachineMarketDto saveMonitorMarket(WashingMachineMarketDto dto) {
        return WashingMachineMarketConverter.toDto(washingMachineMarketRepo.save(WashingMachineMarketConverter.toEntity(dto)));
    }

    public List<WashingMachineMarketDto> saveMonitorMarketList(List<WashingMachineMarketDto> dtoList) {
        return washingMachineMarketRepo.saveAll(dtoList.stream().map(WashingMachineMarketConverter::toEntity).toList()).stream().map(WashingMachineMarketConverter::toDto).toList();
    }

    public WashingMachineMarketDto archiveMonitorMarket(Long id) throws ElementNotFoundException {
        WashingMachineMarketEntity entity = washingMachineMarketRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("washing-machine-market not found"));
        entity.setArchived(true);
        return WashingMachineMarketConverter.toDto(washingMachineMarketRepo.save(entity));
    }

    public WashingMachineMarketDto unarchiveMonitorMarket(Long id) throws ElementNotFoundException {
        WashingMachineMarketEntity entity = washingMachineMarketRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("washing-machine-market not found"));
        entity.setArchived(false);
        return WashingMachineMarketConverter.toDto(washingMachineMarketRepo.save(entity));
    }

    public WashingMachineMarketDto updateMonitorMarket(WashingMachineMarketDto dto) throws ElementNotFoundException {
        WashingMachineMarketEntity entity = washingMachineMarketRepo.findById(dto.getId()).orElseThrow(() -> new ElementNotFoundException("washing-machine-market not found"));
        WashingMachineMarketEntity updatedEntity = WashingMachineMarketBuilder.buildUpdatedMonitorMarketEntity(entity, WashingMachineMarketConverter.toEntity(dto));
        updatedEntity = washingMachineMarketRepo.save(updatedEntity);
        return WashingMachineMarketConverter.toDto(updatedEntity);
    }

    public WashingMachineMarketDto getMonitorMarketById(Long id) throws ElementNotFoundException {
        return WashingMachineMarketConverter.toDto(washingMachineMarketRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("washing-machine-market not found")));
    }

    public List<WashingMachineMarketDto> getAllMonitorMarket() {
        return washingMachineMarketRepo.findAll().stream().map(WashingMachineMarketConverter::toDto).toList();
    }

    public WashingMachineMarketDto insertToRedis(Long id) throws ElementNotFoundException, ElementIsArchivedException {
        WashingMachineMarketEntity entity = washingMachineMarketRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("washing-machine-market not found"));
        if (entity.getArchived()) throw new ElementIsArchivedException("элемент архивирован");
        OuterDto redisDto = RedisBuilder.buildRedisDto(entity);
        entity.setInRedis(true);
        redisRepo.save(redisDto);
        return WashingMachineMarketConverter.toDto(washingMachineMarketRepo.save(entity));
    }

    public WashingMachineMarketDto deleteFromRedis(Long id) throws ElementNotFoundException, ElementIsArchivedException {
        WashingMachineMarketEntity entity = washingMachineMarketRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("washing-machine-market not found"));
        if (entity.getArchived()) throw new ElementIsArchivedException("элемент архивирован");
        entity.setInRedis(false);
        redisRepo.delete(ShopName.CITILINK.getName(), id);
        return null;
    }

    public boolean cleanRedis() {
        return redisRepo.cleanRedis();
    }
}
