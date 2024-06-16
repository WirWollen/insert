package com.parse.steam.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IronMarketDto {
    private Long id;
    private Long marketId;
    private MarketDto market;
    private Long ironId;
    private IronDto iron;
    private String url;
    private Boolean inRedis;
    private Boolean archived;
}
