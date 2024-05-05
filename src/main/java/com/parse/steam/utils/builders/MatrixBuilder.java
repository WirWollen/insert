package com.parse.steam.utils.builders;

import com.parse.steam.entities.MatrixEntity;

public class MatrixBuilder {
    public static MatrixEntity buildUpdatedMatrixEntity(MatrixEntity a, MatrixEntity b) {
        a.setName(b.getName());
        a.setArchived(a.getArchived());
        return a;
    }
}
