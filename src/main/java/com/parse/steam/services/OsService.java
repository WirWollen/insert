package com.parse.steam.services;

import com.parse.steam.converters.OsConverter;
import com.parse.steam.dtos.OsDto;
import com.parse.steam.entities.OsEntity;
import com.parse.steam.exceptions.ElementNotFoundException;
import com.parse.steam.repo.OSRepo;
import com.parse.steam.utils.builders.OsBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OsService {
    private final OSRepo osRepo;

    public OsDto saveOs(OsDto dto) {
        if (dto.getName() == null || Objects.equals(dto.getName(), "")) return null;
        return OsConverter.toDto(osRepo.save(OsConverter.toEntity(dto)));
    }

    public List<OsDto> saveOsList(List<OsDto> dtoList) {
        return osRepo.saveAll(dtoList.stream().map(OsConverter::toEntity).toList()).stream().map(OsConverter::toDto).toList();
    }

    public OsDto archiveOs(Long id) throws ElementNotFoundException {
        OsEntity entity = osRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("os not found"));
        entity.setArchived(true);
        return OsConverter.toDto(osRepo.save(entity));
    }

    public OsDto unarchiveOs(Long id) throws ElementNotFoundException {
        OsEntity entity = osRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("os not found"));
        entity.setArchived(false);
        return OsConverter.toDto(osRepo.save(entity));
    }

    public OsDto updateOs(OsDto dto) throws ElementNotFoundException {
        OsEntity entity = osRepo.findById(dto.getId()).orElseThrow(() -> new ElementNotFoundException("os not found"));
        OsEntity updatedEntity = OsBuilder.buildUpdatedMatrixEntity(entity, OsConverter.toEntity(dto));
        updatedEntity = osRepo.save(updatedEntity);
        return OsConverter.toDto(updatedEntity);
    }

    public OsDto getOsById(Long id) throws ElementNotFoundException {
        return OsConverter.toDto(osRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("os not found")));
    }

    public OsDto getOsByName(String name) throws ElementNotFoundException {
        return OsConverter.toDto(osRepo.findByName(name).orElseThrow(() -> new ElementNotFoundException("os not found")));
    }

    public List<OsDto> getAllOs() {
        return osRepo.findAllOrderByNameAsc().stream().map(OsConverter::toDto).toList();
    }
}
