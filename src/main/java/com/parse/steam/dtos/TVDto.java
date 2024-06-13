package com.parse.steam.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TVDto {
    private Long id;
    private String name;
    private Integer nits;
    private Integer diagonal;
    private Integer hz;
    private Integer gtg;
    private Integer hdmi;
    private Integer dp;
    private SizeDto size;
    private Long sizeId;
    private BrandDto brand;
    private Long brandId;
    private MatrixDto matrix;
    private Long matrixId;
    private OsDto os;
    private Long osId;
    private ScreenTechDto screenTech;
    private Long screenTechId;
    private Boolean archived;
}
