package com.parse.steam.utils.builders;

import com.parse.steam.dtos.redis.OuterDto;
import com.parse.steam.dtos.redis.ShopName;
import com.parse.steam.entities.IronMarketEntity;
import com.parse.steam.entities.MonitorMarketEntity;
import com.parse.steam.entities.TVMarketEntity;
import com.parse.steam.entities.WashingMachineMarketEntity;

public class RedisBuilder {
    public static OuterDto buildRedisDto(MonitorMarketEntity entity) {
        OuterDto outerDto = new OuterDto();
        outerDto.setId(entity.getId());
        outerDto.setShopName(ShopName.CITILINK.getName());
        outerDto.setUrl(entity.getUrl());
        outerDto.setCategoryId(1L);
        return outerDto;
    }

    public static OuterDto buildRedisDto(TVMarketEntity entity) {
        OuterDto outerDto = new OuterDto();
        outerDto.setId(entity.getId());
        outerDto.setShopName(ShopName.CITILINK.getName());
        outerDto.setUrl(entity.getUrl());
        outerDto.setCategoryId(1L);
        return outerDto;
    }

    public static OuterDto buildRedisDto(WashingMachineMarketEntity entity) {
        OuterDto outerDto = new OuterDto();
        outerDto.setId(entity.getId());
        outerDto.setShopName(ShopName.CITILINK.getName());
        outerDto.setUrl(entity.getUrl());
        outerDto.setCategoryId(1L);
        return outerDto;
    }

    public static OuterDto buildRedisDto(IronMarketEntity entity) {
        OuterDto outerDto = new OuterDto();
        outerDto.setId(entity.getId());
        outerDto.setShopName(ShopName.CITILINK.getName());
        outerDto.setUrl(entity.getUrl());
        outerDto.setCategoryId(1L);
        return outerDto;
    }
}
