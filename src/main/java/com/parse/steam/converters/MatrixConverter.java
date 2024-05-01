package com.parse.steam.converters;

import com.parse.steam.dtos.MatrixDto;
import com.parse.steam.entities.MonitorMatrixEntity;
import org.springframework.beans.BeanUtils;

public class MatrixConverter {
    public static MatrixDto toDto(MonitorMatrixEntity entity) {
        MatrixDto dto = new MatrixDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static MonitorMatrixEntity toEntity(MatrixDto dto) {
        MonitorMatrixEntity entity = new MonitorMatrixEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
