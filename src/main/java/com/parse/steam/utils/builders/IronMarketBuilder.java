package com.parse.steam.utils.builders;

import com.parse.steam.entities.IronMarketEntity;

public class IronMarketBuilder {
    public static IronMarketEntity buildUpdatedIronMarketEntity(IronMarketEntity a, IronMarketEntity b) {
        a.setMarket(b.getMarket());
        a.setIron(b.getIron());
        a.setUrl(b.getUrl());
        a.setArchived(b.getArchived());
        return a;
    }
}
