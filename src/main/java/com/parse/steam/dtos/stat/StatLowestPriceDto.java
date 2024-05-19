package com.parse.steam.dtos.stat;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class StatLowestPriceDto {
    private String marketName;
    private Long price;
    private String url;
}
