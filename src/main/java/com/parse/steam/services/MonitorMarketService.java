package com.parse.steam.services;

import com.parse.steam.converters.MonitorMarketConverter;
import com.parse.steam.dtos.MonitorMarketDto;
import com.parse.steam.dtos.redis.OuterDto;
import com.parse.steam.dtos.redis.ShopName;
import com.parse.steam.entities.MonitorMarketEntity;
import com.parse.steam.exceptions.ElementIsArchivedException;
import com.parse.steam.exceptions.ElementNotFoundException;
import com.parse.steam.repo.MonitorMarketRepo;
import com.parse.steam.repo.redis.RedisRepo;
import com.parse.steam.utils.builders.MonitorMarketBuilder;
import com.parse.steam.utils.builders.RedisBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MonitorMarketService {
    private final MonitorMarketRepo monitorMarketRepo;
    private final RedisRepo redisRepo;

    public MonitorMarketDto saveMonitorMarket(MonitorMarketDto dto) {
        return MonitorMarketConverter.toDto(monitorMarketRepo.save(MonitorMarketConverter.toEntity(dto)));
    }

    public List<MonitorMarketDto> saveMonitorMarketList(List<MonitorMarketDto> dtoList) {
        return monitorMarketRepo.saveAll(dtoList.stream().map(MonitorMarketConverter::toEntity).toList()).stream().map(MonitorMarketConverter::toDto).toList();
    }

    public MonitorMarketDto archiveMonitorMarket(Long id) throws ElementNotFoundException {
        MonitorMarketEntity entity = monitorMarketRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("monitor-market not found"));
        entity.setArchived(true);
        return MonitorMarketConverter.toDto(entity);
    }

    public MonitorMarketDto unarchiveMonitorMarket(Long id) throws ElementNotFoundException {
        MonitorMarketEntity entity = monitorMarketRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("monitor-market not found"));
        entity.setArchived(false);
        return MonitorMarketConverter.toDto(entity);
    }

    public MonitorMarketDto updateMonitorMarket(MonitorMarketDto dto) throws ElementNotFoundException {
        MonitorMarketEntity entity = monitorMarketRepo.findById(dto.getId()).orElseThrow(() -> new ElementNotFoundException("monitor-market not found"));
        MonitorMarketEntity updatedEntity = MonitorMarketBuilder.buildUpdatedMonitorMarketEntity(entity, MonitorMarketConverter.toEntity(dto));
        updatedEntity = monitorMarketRepo.save(updatedEntity);
        return MonitorMarketConverter.toDto(updatedEntity);
    }

    public MonitorMarketDto getMonitorMarketById(Long id) throws ElementNotFoundException {
        return MonitorMarketConverter.toDto(monitorMarketRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("monitor-market not found")));
    }

    public List<MonitorMarketDto> getAllMonitorMarket() {
        return monitorMarketRepo.findAll().stream().map(MonitorMarketConverter::toDto).toList();
    }

    public MonitorMarketDto insertToRedis(Long id) throws ElementNotFoundException, ElementIsArchivedException {
        MonitorMarketEntity monitorMarketEntity = monitorMarketRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("monitor-market not found"));
        if (monitorMarketEntity.getArchived()) throw new ElementIsArchivedException("элемент архивирован");
        OuterDto redisDto = RedisBuilder.buildRedisDto(monitorMarketEntity);
        monitorMarketEntity.setInRedis(true);
        redisRepo.save(redisDto);
        return MonitorMarketConverter.toDto(monitorMarketRepo.save(monitorMarketEntity));
    }

    public MonitorMarketDto deleteFromRedis(Long id) throws ElementNotFoundException, ElementIsArchivedException {
        MonitorMarketEntity monitorMarketEntity = monitorMarketRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("monitor-market not found"));
        if (monitorMarketEntity.getArchived()) throw new ElementIsArchivedException("элемент архивирован");
        monitorMarketEntity.setInRedis(false);
        redisRepo.delete(ShopName.CITILINK.getName(), id);
        return MonitorMarketConverter.toDto(monitorMarketRepo.save(monitorMarketEntity));
    }
}
