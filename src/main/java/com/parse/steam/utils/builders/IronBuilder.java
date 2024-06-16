package com.parse.steam.utils.builders;

import com.parse.steam.entities.IronEntity;

public class IronBuilder {
    public static IronEntity buildUpdatedIronEntity(IronEntity a, IronEntity b) {
        a.setName(b.getName());
        a.setAutoOff(b.getAutoOff());
        a.setSteamBoost(b.getSteamBoost());
        a.setPower(b.getPower());
        a.setAutoClean(b.getAutoClean());
        a.setAntiDripSystem(b.getAntiDripSystem());
        a.setIronSole(b.getIronSole());
        a.setBrand(b.getBrand());
        a.setArchived(b.getArchived());
        return a;
    }
}
