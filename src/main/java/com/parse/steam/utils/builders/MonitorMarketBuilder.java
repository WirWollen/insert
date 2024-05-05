package com.parse.steam.utils.builders;

import com.parse.steam.entities.MonitorMarketEntity;

public class MonitorMarketBuilder {
    public static MonitorMarketEntity buildUpdatedMonitorMarketEntity(MonitorMarketEntity a, MonitorMarketEntity b) {
        a.setMarketEntity(b.getMarketEntity());
        a.setMonitorEntity(b.getMonitorEntity());
        a.setUrl(b.getUrl());
        a.setArchived(b.getArchived());
        return a;
    }
}
