package com.parse.steam.utils.builders;

import com.parse.steam.entities.TVEntity;

public class TVBuilder {
    public static TVEntity buildUpdatedTVEntity(TVEntity a, TVEntity b) {
        a.setName(b.getName());
        a.setNits(b.getNits());
        a.setDiagonal(b.getDiagonal());
        a.setHz(b.getHz());
        a.setGtg(b.getGtg());
        a.setHdmi(b.getHdmi());
        a.setDp(b.getDp());
        a.setSize(b.getSize());
        a.setBrand(b.getBrand());
        a.setMatrix(b.getMatrix());
        a.setArchived(b.getArchived());
        a.setOs(b.getOs());
        a.setScreenTech(b.getScreenTech());
        return a;
    }
}
