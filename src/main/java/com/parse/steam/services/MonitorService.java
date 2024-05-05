package com.parse.steam.services;

import com.parse.steam.converters.MonitorConverter;
import com.parse.steam.dtos.MonitorDto;
import com.parse.steam.entities.MonitorEntity;
import com.parse.steam.exceptions.ElementNotFoundException;
import com.parse.steam.repo.MonitorRepo;
import com.parse.steam.utils.builders.MonitorBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MonitorService {
    private final MonitorRepo monitorRepo;

    public MonitorDto saveMonitor(MonitorDto dto) {
        return MonitorConverter.toDto(monitorRepo.save(MonitorConverter.toEntity(dto)));
    }

    public List<MonitorDto> saveMonitorList(List<MonitorDto> dtoList) {
        return monitorRepo.saveAll(dtoList.stream().map(MonitorConverter::toEntity).toList()).stream().map(MonitorConverter::toDto).toList();
    }

    public MonitorDto archiveMonitor(Long id) throws ElementNotFoundException {
        MonitorEntity entity = monitorRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("monitor not found"));
        entity.setArchived(true);
        return MonitorConverter.toDto(entity);
    }

    public MonitorDto unarchiveMonitor(Long id) throws ElementNotFoundException {
        MonitorEntity entity = monitorRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("monitor not found"));
        entity.setArchived(false);
        return MonitorConverter.toDto(entity);
    }

    public MonitorDto updateMonitor(MonitorDto dto) throws ElementNotFoundException {
        MonitorEntity entity = monitorRepo.findById(dto.getId()).orElseThrow(() -> new ElementNotFoundException("monitor not found"));
        MonitorEntity updatedEntity = MonitorBuilder.buildUpdatedMonitorEntity(entity, MonitorConverter.toEntity(dto));
        updatedEntity = monitorRepo.save(updatedEntity);
        return MonitorConverter.toDto(updatedEntity);
    }

    public MonitorDto getMonitorById(Long id) throws ElementNotFoundException {
        return MonitorConverter.toDto(monitorRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("monitor not found")));
    }

    public List<MonitorDto> getAllMonitor() {
        return monitorRepo.findAll().stream().map(MonitorConverter::toDto).toList();
    }

}
