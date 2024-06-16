package com.parse.steam.utils.builders;

import com.parse.steam.entities.OsEntity;

public class OsBuilder {
    public static OsEntity buildUpdatedMatrixEntity(OsEntity a, OsEntity b) {
        a.setName(b.getName());
        a.setArchived(b.getArchived());
        return a;
    }
}
