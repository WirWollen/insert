package com.parse.steam.utils.builders;

import com.parse.steam.entities.WashingMachineEntity;

public class WashingMachineBuilder {
    public static WashingMachineEntity buildUpdatedWashingMachineEntity(WashingMachineEntity a, WashingMachineEntity b) {
        a.setName(b.getName());
        a.setDisplay(b.getDisplay());
        a.setDepth(b.getDepth());
        a.setVerticalLoadType(b.getVerticalLoadType());
        a.setBrand(b.getBrand());
        a.setWashTech(b.getWashTech());
        a.setArchived(b.getArchived());
        return a;
    }
}
