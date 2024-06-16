package com.parse.steam.utils.builders;

import com.parse.steam.entities.WashTechEntity;

public class WashTechBuilder {
    public static WashTechEntity buildUpdatedMatrixEntity(WashTechEntity a, WashTechEntity b) {
        a.setName(b.getName());
        a.setArchived(b.getArchived());
        return a;
    }
}
