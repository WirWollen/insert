package com.parse.steam.utils.builders;

import com.parse.steam.entities.SizeEntity;

public class SizeBuilder {
    public static SizeEntity buildUpdatedSizeEntity(SizeEntity a, SizeEntity b) {
        a.setSizeX(b.getSizeX());
        a.setSizeY(b.getSizeY());
        a.setArchived(b.getArchived());
        return a;
    }
}
