package com.parse.steam.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WashingMachineMarketDto {
    private Long marketId;
    private MarketDto market;
    private Long washingMachineId;
    private WashingMachineDto washingMachine;
    private String url;
    private Boolean inRedis;
    private Boolean archived;
}
