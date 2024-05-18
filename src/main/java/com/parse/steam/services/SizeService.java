package com.parse.steam.services;

import com.parse.steam.converters.SizeConverter;
import com.parse.steam.dtos.SizeDto;
import com.parse.steam.entities.SizeEntity;
import com.parse.steam.exceptions.ElementNotFoundException;
import com.parse.steam.repo.SizeRepo;
import com.parse.steam.utils.builders.SizeBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SizeService {
    private final SizeRepo sizeRepo;

    public SizeDto saveSize(SizeDto dto) {
        return SizeConverter.toDto(sizeRepo.save(SizeConverter.toEntity(dto)));
    }

    public List<SizeDto> saveSizeList(List<SizeDto> dtoList) {
        return sizeRepo.saveAll(dtoList.stream().map(SizeConverter::toEntity).toList()).stream().map(SizeConverter::toDto).toList();
    }

    public SizeDto archiveSize(Long id) throws ElementNotFoundException {
        SizeEntity entity = sizeRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("size not found"));
        entity.setArchived(true);
        return SizeConverter.toDto(sizeRepo.save(entity));
    }

    public SizeDto unarchiveSize(Long id) throws ElementNotFoundException {
        SizeEntity entity = sizeRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("size not found"));
        entity.setArchived(false);
        return SizeConverter.toDto(sizeRepo.save(entity));
    }

    public SizeDto updateSize(SizeDto dto) throws ElementNotFoundException {
        SizeEntity entity = sizeRepo.findById(dto.getId()).orElseThrow(() -> new ElementNotFoundException("size not found"));
        SizeEntity updatedEntity = SizeBuilder.buildUpdatedSizeEntity(entity, SizeConverter.toEntity(dto));
        updatedEntity = sizeRepo.save(updatedEntity);
        return SizeConverter.toDto(updatedEntity);
    }

    public SizeDto getSizeById(Long id) throws ElementNotFoundException {
        return SizeConverter.toDto(sizeRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("size not found")));
    }

    public List<SizeDto> getAllSize() {
        return sizeRepo.findAll().stream().map(SizeConverter::toDto).toList();
    }
}
