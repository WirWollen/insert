package com.parse.steam.utils.builders;

import com.parse.steam.entities.BrandEntity;

public class BrandBuilder {
    public static BrandEntity buildUpdatedMatrixEntity(BrandEntity a, BrandEntity b) {
        a.setName(b.getName());
        a.setArchived(b.getArchived());
        return a;
    }
}
