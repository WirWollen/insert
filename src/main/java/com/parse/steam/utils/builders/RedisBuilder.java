package com.parse.steam.utils.builders;

import com.parse.steam.dtos.redis.OuterDto;
import com.parse.steam.dtos.redis.ShopName;
import com.parse.steam.entities.MonitorMarketEntity;

public class RedisBuilder {
    public static OuterDto buildRedisDto(MonitorMarketEntity entity) {
        OuterDto outerDto = new OuterDto();
        outerDto.setId(entity.getId());
        outerDto.setShopName(ShopName.CITILINK);
        outerDto.setUrl(entity.getUrl());
        return outerDto;
    }
}
