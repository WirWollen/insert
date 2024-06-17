package com.parse.steam.utils.builders;

import com.parse.steam.entities.MonitorEntity;

public class MonitorBuilder {
    public static MonitorEntity buildUpdatedMonitorEntity(MonitorEntity a, MonitorEntity b) {
        a.setName(b.getName());
        a.setNits(b.getNits());
        a.setDiagonal(b.getDiagonal());
        a.setHz(b.getHz());
        a.setGtg(b.getGtg());
        a.setHdmi(b.getHdmi());
        a.setDp(b.getDp());
        a.setVga(b.getVga());
        a.setSize(b.getSize());
        a.setBrand(b.getBrand());
        a.setMatrix(b.getMatrix());
        a.setArchived(b.getArchived());
        return a;
    }
}
