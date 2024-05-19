package com.parse.steam.dtos.stat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatCountMonitorMarketDto {
    private String marketName;
    private Long count;
}
