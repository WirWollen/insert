package com.parse.steam.services;

import com.parse.steam.dtos.OutputItemDto;
import com.parse.steam.entities.MonitorStatEntity;
import com.parse.steam.repo.parsed.MonitorStatRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateDBService {
    private final MonitorStatRepo monitorStatRepo;

    @Transactional
    public void insert(OutputItemDto dto) {
        MonitorStatEntity entity = new MonitorStatEntity();
        entity.setMonitorMarketId(dto.getId());
        entity.setPrice(dto.getPrice());
        entity.setMoment(dto.getTimeChecking());

        monitorStatRepo.save(entity);
    }

}
