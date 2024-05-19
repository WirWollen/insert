package com.parse.steam.dtos.stat;

import com.parse.steam.dtos.MonitorStatDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatElDto {
    private String marketName;
    private List<MonitorStatDto> items;
}
