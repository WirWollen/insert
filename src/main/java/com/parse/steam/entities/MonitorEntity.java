package com.parse.steam.entities;

import com.parse.steam.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "monitor")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MonitorEntity extends BaseEntity {
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

    @Column(name = "vga")
    private Integer vga;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "size_id")
    private SizeEntity size;

    @Column(name = "size_id", updatable = false, insertable = false)
    private Long sizeId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id")
    private BrandEntity brand;

    @Column(name = "brand_id", updatable = false, insertable = false)
    private Long brandId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "matrix_id")
    private MatrixEntity matrix;

    @Column(name = "matrix_id", updatable = false, insertable = false)
    private Long matrixId;

    @Column(name = "archived")
    private Boolean archived;
}
