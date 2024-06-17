package com.parse.steam.services;

import com.parse.steam.converters.OsConverter;
import com.parse.steam.converters.ScreenTechConverter;
import com.parse.steam.dtos.OsDto;
import com.parse.steam.dtos.ScreenTechDto;
import com.parse.steam.entities.ScreenTechEntity;
import com.parse.steam.exceptions.ElementNotFoundException;
import com.parse.steam.repo.ScreenTechRepo;
import com.parse.steam.utils.builders.ScreenTechBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScreenTechService {
    private final ScreenTechRepo screenTechRepo;

    public ScreenTechDto saveScreenTech(ScreenTechDto dto) {
        return ScreenTechConverter.toDto(screenTechRepo.save(ScreenTechConverter.toEntity(dto)));
    }

    public List<ScreenTechDto> saveScreenTechList(List<ScreenTechDto> dtoList) {
        return screenTechRepo.saveAll(dtoList.stream().map(ScreenTechConverter::toEntity).toList()).stream().map(ScreenTechConverter::toDto).toList();
    }

    public ScreenTechDto archiveScreenTech(Long id) throws ElementNotFoundException {
        ScreenTechEntity entity = screenTechRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("screen tech not found"));
        entity.setArchived(true);
        return ScreenTechConverter.toDto(screenTechRepo.save(entity));
    }

    public ScreenTechDto unarchiveScreenTech(Long id) throws ElementNotFoundException {
        ScreenTechEntity entity = screenTechRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("screen tech not found"));
        entity.setArchived(false);
        return ScreenTechConverter.toDto(screenTechRepo.save(entity));
    }

    public ScreenTechDto updateScreenTech(ScreenTechDto dto) throws ElementNotFoundException {
        ScreenTechEntity entity = screenTechRepo.findById(dto.getId()).orElseThrow(() -> new ElementNotFoundException("screen tech not found"));
        ScreenTechEntity updatedEntity = ScreenTechBuilder.buildUpdatedMatrixEntity(entity, ScreenTechConverter.toEntity(dto));
        updatedEntity = screenTechRepo.save(updatedEntity);
        return ScreenTechConverter.toDto(updatedEntity);
    }

    public ScreenTechDto getScreenTechById(Long id) throws ElementNotFoundException {
        return ScreenTechConverter.toDto(screenTechRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("screen tech not found")));
    }

    public List<ScreenTechDto> getAllScreenTech() {
        return screenTechRepo.findAll().stream().map(ScreenTechConverter::toDto).toList();
    }

    public ScreenTechDto getScreenTechByName(String name) throws ElementNotFoundException {
        return ScreenTechConverter.toDto(screenTechRepo.findByName(name).orElseThrow(() -> new ElementNotFoundException("creen tech not found")));
    }
}
