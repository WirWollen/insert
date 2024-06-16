package com.parse.steam.services;

import com.parse.steam.converters.IronConverter;
import com.parse.steam.dtos.IronDto;
import com.parse.steam.entities.IronEntity;
import com.parse.steam.exceptions.ElementNotFoundException;
import com.parse.steam.repo.IronRepo;
import com.parse.steam.utils.builders.IronBuilder;
import com.parse.steam.utils.builders.MonitorBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IronService {
    private final IronRepo ironRepo;

    public IronDto saveIron(IronDto dto) {
        return IronConverter.toDto(ironRepo.save(IronConverter.toEntity(dto)));
    }

    public List<IronDto> saveIronList(List<IronDto> dtoList) {
        return ironRepo.saveAll(dtoList.stream().map(IronConverter::toEntity).toList()).stream().map(IronConverter::toDto).toList();
    }

    public IronDto archiveIron(Long id) throws ElementNotFoundException {
        IronEntity entity = ironRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("iron not found"));
        entity.setArchived(true);
        return IronConverter.toDto(ironRepo.save(entity));
    }

    public IronDto unarchiveIron(Long id) throws ElementNotFoundException {
        IronEntity entity = ironRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("iron not found"));
        entity.setArchived(false);
        return IronConverter.toDto(ironRepo.save(entity));
    }

    public IronDto updateIron(IronDto dto) throws ElementNotFoundException {
        IronEntity entity = ironRepo.findById(dto.getId()).orElseThrow(() -> new ElementNotFoundException("iron not found"));
        IronEntity updatedEntity = IronBuilder.buildUpdatedIronEntity(entity, IronConverter.toEntity(dto));
        updatedEntity = ironRepo.save(updatedEntity);
        return IronConverter.toDto(updatedEntity);
    }

    public IronDto getIronById(Long id) throws ElementNotFoundException {
        return IronConverter.toDto(ironRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("iron not found")));
    }

    public List<IronDto> getAllIron() {
        return ironRepo.findAll().stream().map(IronConverter::toDto).toList();
    }

}
