package com.parse.steam.services;

import com.parse.steam.converters.MonitorConverter;
import com.parse.steam.dtos.MonitorDto;
import com.parse.steam.entities.MonitorEntity;
import com.parse.steam.repo.MonitorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InsertModelService {
    private final MonitorRepo monitorRepo;

    public MonitorDto insertMonitor(MonitorDto monitorDto) {
        return MonitorConverter.toDto(monitorRepo.save(MonitorConverter.toEntity(monitorDto)));
    }

    public List<MonitorDto> insertMonitorList(List<MonitorDto> monitorDtoList) {
        List<MonitorEntity> entities = monitorDtoList.stream().map(MonitorConverter::toEntity).toList();
        return monitorRepo.saveAll(entities).stream().map(MonitorConverter::toDto).toList();
    }

}
