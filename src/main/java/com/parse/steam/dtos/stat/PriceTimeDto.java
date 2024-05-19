package com.parse.steam.dtos.stat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class PriceTimeDto {
    private String marketName;
    private List<TimePriceDto> items;
}
