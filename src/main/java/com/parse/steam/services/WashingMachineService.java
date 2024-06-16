package com.parse.steam.services;

import com.parse.steam.converters.WashingMachineConverter;
import com.parse.steam.dtos.WashingMachineDto;
import com.parse.steam.entities.WashingMachineEntity;
import com.parse.steam.exceptions.ElementNotFoundException;
import com.parse.steam.repo.WashingMachineRepo;
import com.parse.steam.utils.builders.WashingMachineBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WashingMachineService {
    private final WashingMachineRepo washingMachineRepo;

    public WashingMachineDto saveWashingMachine(WashingMachineDto dto) {
        return WashingMachineConverter.toDto(washingMachineRepo.save(WashingMachineConverter.toEntity(dto)));
    }

    public List<WashingMachineDto> saveWashingMachineList(List<WashingMachineDto> dtoList) {
        return washingMachineRepo.saveAll(dtoList.stream().map(WashingMachineConverter::toEntity).toList()).stream().map(WashingMachineConverter::toDto).toList();
    }

    public WashingMachineDto archiveWashingMachine(Long id) throws ElementNotFoundException {
        WashingMachineEntity entity = washingMachineRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("washing machine not found"));
        entity.setArchived(true);
        return WashingMachineConverter.toDto(washingMachineRepo.save(entity));
    }

    public WashingMachineDto unarchiveWashingMachine(Long id) throws ElementNotFoundException {
        WashingMachineEntity entity = washingMachineRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("washing machine not found"));
        entity.setArchived(false);
        return WashingMachineConverter.toDto(washingMachineRepo.save(entity));
    }

    public WashingMachineDto updateWashingMachine(WashingMachineDto dto) throws ElementNotFoundException {
        WashingMachineEntity entity = washingMachineRepo.findById(dto.getId()).orElseThrow(() -> new ElementNotFoundException("washing machine not found"));
        WashingMachineEntity updatedEntity = WashingMachineBuilder.buildUpdatedWashingMachineEntity(entity, WashingMachineConverter.toEntity(dto));
        updatedEntity = washingMachineRepo.save(updatedEntity);
        return WashingMachineConverter.toDto(updatedEntity);
    }

    public WashingMachineDto getWashingMachineById(Long id) throws ElementNotFoundException {
        return WashingMachineConverter.toDto(washingMachineRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("washing machine not found")));
    }

    public List<WashingMachineDto> getAllWashingMachine() {
        return washingMachineRepo.findAll().stream().map(WashingMachineConverter::toDto).toList();
    }

}
