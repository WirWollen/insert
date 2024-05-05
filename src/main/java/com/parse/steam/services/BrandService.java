package com.parse.steam.services;

import com.parse.steam.converters.BrandConverter;
import com.parse.steam.dtos.BrandDto;
import com.parse.steam.entities.BrandEntity;
import com.parse.steam.exceptions.ElementNotFoundException;
import com.parse.steam.repo.BrandRepo;
import com.parse.steam.utils.builders.BrandBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepo brandRepo;

    public BrandDto saveBrand(BrandDto dto) {
        return BrandConverter.toDto(brandRepo.save(BrandConverter.toEntity(dto)));
    }

    public List<BrandDto> saveBrandList(List<BrandDto> dtoList) {
        return brandRepo.saveAll(dtoList.stream().map(BrandConverter::toEntity).toList()).stream().map(BrandConverter::toDto).toList();
    }

    public BrandDto archiveBrand(Long id) throws ElementNotFoundException {
        BrandEntity entity = brandRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("brand not found"));
        entity.setArchived(true);
        return BrandConverter.toDto(entity);
    }

    public BrandDto unarchiveBrand(Long id) throws ElementNotFoundException {
        BrandEntity entity = brandRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("brand not found"));
        entity.setArchived(false);
        return BrandConverter.toDto(entity);
    }

    public BrandDto updateBrand(BrandDto dto) throws ElementNotFoundException {
        BrandEntity entity = brandRepo.findById(dto.getId()).orElseThrow(() -> new ElementNotFoundException("brand not found"));
        BrandEntity updatedEntity = BrandBuilder.buildUpdatedMatrixEntity(entity, BrandConverter.toEntity(dto));
        updatedEntity = brandRepo.save(updatedEntity);
        return BrandConverter.toDto(updatedEntity);
    }

    public BrandDto getBrandById(Long id) throws ElementNotFoundException {
        return BrandConverter.toDto(brandRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("brand not found")));
    }

    public BrandDto getBrandByName(String name) throws ElementNotFoundException {
        return BrandConverter.toDto(brandRepo.findByName(name).orElseThrow(() -> new ElementNotFoundException("brand not found")));
    }

    public List<BrandDto> getAllBrand() {
        return brandRepo.findAllOrderByNameAsc().stream().map(BrandConverter::toDto).toList();
    }
}
