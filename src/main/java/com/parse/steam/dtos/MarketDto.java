package com.parse.steam.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class MarketDto {
    private Long id;
    private String guid;
    private String name;
    private String url;
    private Boolean archived;
}
