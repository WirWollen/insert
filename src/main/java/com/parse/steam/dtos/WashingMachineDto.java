package com.parse.steam.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WashingMachineDto {
    private String name;
    private Boolean display;
    private Integer depth;
    private Boolean verticalLoadType;
    private BrandDto brand;
    private Long brandId;
    private WashTechDto washTech;
    private Long washTechId;
    private Boolean archived;
}
