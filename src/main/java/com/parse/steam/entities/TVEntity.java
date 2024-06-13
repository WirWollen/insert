package com.parse.steam.entities;

import com.parse.steam.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "tv")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TVEntity extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "nits")
    private Integer nits;

    @Column(name = "diagonal")
    private Integer diagonal;

    @Column(name = "hz")
    private Integer hz;

    @Column(name = "gtg")
    private Integer gtg;

    @Column(name = "hdmi")
    private Integer hdmi;

    @Column(name = "dp")
    private Integer dp;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "size_id", updatable = false, insertable = false)
    private SizeEntity size;

    @Column(name = "size_id")
    private Long sizeId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id", updatable = false, insertable = false)
    private BrandEntity brand;

    @Column(name = "brand_id")
    private Long brandId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "matrix_id", updatable = false, insertable = false)
    private MatrixEntity matrix;

    @Column(name = "matrix_id")
    private Long matrixId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "os", updatable = false, insertable = false)
    private OsEntity os;

    @Column(name = "os")
    private Long osId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "screen_tech", updatable = false, insertable = false)
    private ScreenTechEntity screenTech;

    @Column(name = "screen_tech")
    private Long screenTechId;

    @Column(name = "archived")
    private Boolean archived;
}
