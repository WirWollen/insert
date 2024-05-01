package com.parse.steam.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OutputItemDto {
    private Long id;
    private Long price;
    private LocalDateTime timeChecking;
}
