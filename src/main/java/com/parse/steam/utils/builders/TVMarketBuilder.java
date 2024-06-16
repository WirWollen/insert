package com.parse.steam.utils.builders;

import com.parse.steam.entities.TVMarketEntity;

public class TVMarketBuilder {
    public static TVMarketEntity buildUpdatedTVMarketEntity(TVMarketEntity a, TVMarketEntity b) {
        a.setMarket(b.getMarket());
        a.setTv(b.getTv());
        a.setUrl(b.getUrl());
        a.setArchived(b.getArchived());
        return a;
    }
}
