package com.parse.steam.services;

import com.parse.steam.converters.IronSoleConverter;
import com.parse.steam.dtos.IronSoleDto;
import com.parse.steam.entities.IronSoleEntity;
import com.parse.steam.exceptions.ElementNotFoundException;
import com.parse.steam.repo.IronSoleRepo;
import com.parse.steam.utils.builders.IronSoleBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class IronSoleService {
    private final IronSoleRepo ironSoleRepo;

    public IronSoleDto saveIronSole(IronSoleDto dto) {
        if (dto.getName() == null || Objects.equals(dto.getName(), "")) return null;
        return IronSoleConverter.toDto(ironSoleRepo.save(IronSoleConverter.toEntity(dto)));
    }

    public List<IronSoleDto> saveIronSoleList(List<IronSoleDto> dtoList) {
        return ironSoleRepo.saveAll(dtoList.stream().map(IronSoleConverter::toEntity).toList()).stream().map(IronSoleConverter::toDto).toList();
    }

    public IronSoleDto archiveIronSole(Long id) throws ElementNotFoundException {
        IronSoleEntity entity = ironSoleRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("iron sole not found"));
        entity.setArchived(true);
        return IronSoleConverter.toDto(ironSoleRepo.save(entity));
    }

    public IronSoleDto unarchiveIronSole(Long id) throws ElementNotFoundException {
        IronSoleEntity entity = ironSoleRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("iron sole not found"));
        entity.setArchived(false);
        return IronSoleConverter.toDto(ironSoleRepo.save(entity));
    }

    public IronSoleDto updateIronSole(IronSoleDto dto) throws ElementNotFoundException {
        IronSoleEntity entity = ironSoleRepo.findById(dto.getId()).orElseThrow(() -> new ElementNotFoundException("iron sole not found"));
        IronSoleEntity updatedEntity = IronSoleBuilder.buildUpdatedMatrixEntity(entity, IronSoleConverter.toEntity(dto));
        updatedEntity = ironSoleRepo.save(updatedEntity);
        return IronSoleConverter.toDto(updatedEntity);
    }

    public IronSoleDto getIronSoleById(Long id) throws ElementNotFoundException {
        return IronSoleConverter.toDto(ironSoleRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("iron sole not found")));
    }

    public IronSoleDto getIronSoleByName(String name) throws ElementNotFoundException {
        return IronSoleConverter.toDto(ironSoleRepo.findByName(name).orElseThrow(() -> new ElementNotFoundException("iron sole not found")));
    }

    public List<IronSoleDto> getAllIronSole() {
        return ironSoleRepo.findAllOrderByNameAsc().stream().map(IronSoleConverter::toDto).toList();
    }
}
