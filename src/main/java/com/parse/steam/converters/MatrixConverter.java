package com.parse.steam.converters;

import com.parse.steam.dtos.MatrixDto;
import com.parse.steam.entities.MatrixEntity;
import org.springframework.beans.BeanUtils;

public class MatrixConverter {
    public static MatrixDto toDto(MatrixEntity entity) {
        MatrixDto dto = new MatrixDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static MatrixEntity toEntity(MatrixDto dto) {
        MatrixEntity entity = new MatrixEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
