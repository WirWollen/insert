package com.parse.steam.utils.builders;

import com.parse.steam.entities.MarketEntity;

public class MarketBuilder {
    public static MarketEntity buildUpdatedMarketEntity(MarketEntity a, MarketEntity b) {
        a.setName(b.getName());
        a.setGuid(b.getGuid());
        a.setUrl(b.getUrl());
        a.setArchived(b.getArchived());
        return a;
    }
}
