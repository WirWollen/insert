package com.parse.steam.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class IronStatDto {
    private Long id;
    private Long monitorMarketId;
    private IronMarketDto ironMarket;
    private Long price;
    private LocalDateTime moment;
}
