package com.parse.steam.dtos.stat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class TimePriceDto {
    private LocalDateTime time;
    private Long price;
}
