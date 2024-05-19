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
public class MonitorStatDto {
    private Long id;
    private Long monitorMarketId;
    private MonitorMarketDto monitorMarketDto;
    private Long price;
    private LocalDateTime moment;
}
