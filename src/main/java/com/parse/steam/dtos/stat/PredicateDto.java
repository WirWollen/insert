package com.parse.steam.dtos.stat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class PredicateDto {
    private StatLowestPriceDto statLowestPriceDto;
    private String status;
}
