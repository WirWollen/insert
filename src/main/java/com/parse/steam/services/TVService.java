package com.parse.steam.services;

import com.parse.steam.converters.TVConverter;
import com.parse.steam.dtos.TVDto;
import com.parse.steam.entities.TVEntity;
import com.parse.steam.exceptions.ElementNotFoundException;
import com.parse.steam.repo.TVRepo;
import com.parse.steam.utils.builders.TVBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TVService {
    private final TVRepo tvRepo;

    public TVDto saveTV(TVDto dto) {
        return TVConverter.toDto(tvRepo.save(TVConverter.toEntity(dto)));
    }

    public List<TVDto> saveTVList(List<TVDto> dtoList) {
        return tvRepo.saveAll(dtoList.stream().map(TVConverter::toEntity).toList()).stream().map(TVConverter::toDto).toList();
    }

    public TVDto archiveTV(Long id) throws ElementNotFoundException {
        TVEntity entity = tvRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("TV not found"));
        entity.setArchived(true);
        return TVConverter.toDto(tvRepo.save(entity));
    }

    public TVDto unarchiveTV(Long id) throws ElementNotFoundException {
        TVEntity entity = tvRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("TV not found"));
        entity.setArchived(false);
        return TVConverter.toDto(tvRepo.save(entity));
    }

    public TVDto updateTV(TVDto dto) throws ElementNotFoundException {
        TVEntity entity = tvRepo.findById(dto.getId()).orElseThrow(() -> new ElementNotFoundException("TV not found"));
        TVEntity updatedEntity = TVBuilder.buildUpdatedTVEntity(entity, TVConverter.toEntity(dto));
        updatedEntity = tvRepo.save(updatedEntity);
        return TVConverter.toDto(updatedEntity);
    }

    public TVDto getTVById(Long id) throws ElementNotFoundException {
        return TVConverter.toDto(tvRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("TV not found")));
    }

    public List<TVDto> getAllTV() {
        return tvRepo.findAll().stream().map(TVConverter::toDto).toList();
    }

}
