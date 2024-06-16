package com.parse.steam.services;

import com.parse.steam.converters.WashTechConverter;
import com.parse.steam.dtos.WashTechDto;
import com.parse.steam.entities.WashTechEntity;
import com.parse.steam.exceptions.ElementNotFoundException;
import com.parse.steam.repo.WashTechRepo;
import com.parse.steam.utils.builders.WashTechBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class WashTechService {
    private final WashTechRepo washTechRepo;

    public WashTechDto saveWashTech(WashTechDto dto) {
        if (dto.getName() == null || Objects.equals(dto.getName(), "")) return null;
        return WashTechConverter.toDto(washTechRepo.save(WashTechConverter.toEntity(dto)));
    }

    public List<WashTechDto> saveWashTechList(List<WashTechDto> dtoList) {
        return washTechRepo.saveAll(dtoList.stream().map(WashTechConverter::toEntity).toList()).stream().map(WashTechConverter::toDto).toList();
    }

    public WashTechDto archiveWashTech(Long id) throws ElementNotFoundException {
        WashTechEntity entity = washTechRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("wash tech not found"));
        entity.setArchived(true);
        return WashTechConverter.toDto(washTechRepo.save(entity));
    }

    public WashTechDto unarchiveWashTech(Long id) throws ElementNotFoundException {
        WashTechEntity entity = washTechRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("wash tech not found"));
        entity.setArchived(false);
        return WashTechConverter.toDto(washTechRepo.save(entity));
    }

    public WashTechDto updateWashTech(WashTechDto dto) throws ElementNotFoundException {
        WashTechEntity entity = washTechRepo.findById(dto.getId()).orElseThrow(() -> new ElementNotFoundException("wash tech not found"));
        WashTechEntity updatedEntity = WashTechBuilder.buildUpdatedMatrixEntity(entity, WashTechConverter.toEntity(dto));
        updatedEntity = washTechRepo.save(updatedEntity);
        return WashTechConverter.toDto(updatedEntity);
    }

    public WashTechDto getWashTechById(Long id) throws ElementNotFoundException {
        return WashTechConverter.toDto(washTechRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("wash tech not found")));
    }

    public WashTechDto getWashTechByName(String name) throws ElementNotFoundException {
        return WashTechConverter.toDto(washTechRepo.findByName(name).orElseThrow(() -> new ElementNotFoundException("wash tech not found")));
    }

    public List<WashTechDto> getAllWashTech() {
        return washTechRepo.findAllOrderByNameAsc().stream().map(WashTechConverter::toDto).toList();
    }
}
