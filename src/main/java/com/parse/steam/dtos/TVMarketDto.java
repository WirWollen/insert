package com.parse.steam.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TVMarketDto {
    private Long marketId;
    private MarketDto market;
    private Long tvId;
    private TVDto tv;
    private String url;
    private Boolean inRedis;
    private Boolean archived;
}
