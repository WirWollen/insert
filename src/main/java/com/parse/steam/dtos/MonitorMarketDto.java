package com.parse.steam.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class MonitorMarketDto {
    private Long id;
    private Long marketId;
    private MarketDto marketDto;
    private Long monitorId;
    private MonitorDto monitorDto;
    private String url;
    private Boolean inRedis;
    private Boolean archived;
}
