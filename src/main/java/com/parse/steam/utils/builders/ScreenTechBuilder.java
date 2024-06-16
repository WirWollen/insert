package com.parse.steam.utils.builders;

import com.parse.steam.entities.ScreenTechEntity;

public class ScreenTechBuilder {
    public static ScreenTechEntity buildUpdatedMatrixEntity(ScreenTechEntity a, ScreenTechEntity b) {
        a.setName(b.getName());
        a.setArchived(b.getArchived());
        return a;
    }
}
