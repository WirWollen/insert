package com.parse.steam.utils.builders;

import com.parse.steam.entities.WashingMachineMarketEntity;

public class WashingMachineMarketBuilder {
    public static WashingMachineMarketEntity buildUpdatedMonitorMarketEntity(WashingMachineMarketEntity a, WashingMachineMarketEntity b) {
        a.setMarketEntity(b.getMarketEntity());
        a.setWashingMachine(b.getWashingMachine());
        a.setUrl(b.getUrl());
        a.setArchived(b.getArchived());
        return a;
    }
}
