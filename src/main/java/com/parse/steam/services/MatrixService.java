package com.parse.steam.services;

import com.parse.steam.converters.MatrixConverter;
import com.parse.steam.converters.SizeConverter;
import com.parse.steam.dtos.MatrixDto;
import com.parse.steam.entities.MatrixEntity;
import com.parse.steam.exceptions.ElementNotFoundException;
import com.parse.steam.repo.MatrixRepo;
import com.parse.steam.utils.builders.MatrixBuilder;
import com.parse.steam.utils.builders.SizeBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatrixService {
    private final MatrixRepo matrixRepo;

    public MatrixDto saveMatrix(MatrixDto dto) {
        return MatrixConverter.toDto(matrixRepo.save(MatrixConverter.toEntity(dto)));
    }

    public List<MatrixDto> saveMatrixList(List<MatrixDto> dtoList) {
        return matrixRepo.saveAll(dtoList.stream().map(MatrixConverter::toEntity).toList()).stream().map(MatrixConverter::toDto).toList();
    }

    public MatrixDto archiveMatrix(Long id) throws ElementNotFoundException {
        MatrixEntity entity = matrixRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("matrix not found"));
        entity.setArchived(true);
        return MatrixConverter.toDto(entity);
    }

    public MatrixDto unarchiveMatrix(Long id) throws ElementNotFoundException {
        MatrixEntity entity = matrixRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("matrix not found"));
        entity.setArchived(false);
        return MatrixConverter.toDto(entity);
    }

    public MatrixDto updateMatrix(MatrixDto dto) throws ElementNotFoundException {
        MatrixEntity entity = matrixRepo.findById(dto.getId()).orElseThrow(() -> new ElementNotFoundException("matrix not found"));
        MatrixEntity updatedEntity = MatrixBuilder.buildUpdatedMatrixEntity(entity, MatrixConverter.toEntity(dto));
        updatedEntity = matrixRepo.save(updatedEntity);
        return MatrixConverter.toDto(updatedEntity);
    }

    public MatrixDto getMatrixById(Long id) throws ElementNotFoundException {
        return MatrixConverter.toDto(matrixRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("matrix not found")));
    }

    public List<MatrixDto> getAllMatrix() {
        return matrixRepo.findAll().stream().map(MatrixConverter::toDto).toList();
    }
}
