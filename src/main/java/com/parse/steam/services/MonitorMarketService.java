package com.parse.steam.services;

import com.parse.steam.converters.MonitorMarketConverter;
import com.parse.steam.dtos.MonitorMarketDto;
import com.parse.steam.dtos.redis.OuterDto;
import com.parse.steam.entities.MonitorMarketEntity;
import com.parse.steam.repo.MonitorMarketRepo;
import com.parse.steam.repo.MonitorRepo;
import com.parse.steam.repo.redis.RedisRepo;
import com.parse.steam.utils.builders.RedisBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MonitorMarketService {
    private final MonitorMarketRepo monitorMarketRepo;
    private final RedisRepo redisRepo;

    public MonitorMarketDto insertMonitorMarket(MonitorMarketDto monitorMarketDto) {
        MonitorMarketEntity monitorMarketEntity = monitorMarketRepo.save(MonitorMarketConverter.toEntity(monitorMarketDto));
        OuterDto redisDto = RedisBuilder.buildRedisDto(monitorMarketEntity);
        redisRepo.save(redisDto);
        return MonitorMarketConverter.toDto(monitorMarketEntity);
    }
}
