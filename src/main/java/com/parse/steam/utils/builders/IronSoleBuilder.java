package com.parse.steam.utils.builders;

import com.parse.steam.entities.IronSoleEntity;

public class IronSoleBuilder {
    public static IronSoleEntity buildUpdatedMatrixEntity(IronSoleEntity a, IronSoleEntity b) {
        a.setName(b.getName());
        a.setArchived(b.getArchived());
        return a;
    }
}
