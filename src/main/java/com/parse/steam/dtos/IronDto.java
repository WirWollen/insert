package com.parse.steam.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IronDto {
    private String name;
    private Boolean autoOff;
    private Boolean steamBoost;
    private Integer power;
    private Boolean autoClean;
    private Boolean antiDripSystem;
    private BrandDto brand;
    private Long brandId;
    private IronSoleDto ironSole;
    private Long ironSoleId;
    private Boolean archived;
}
