package com.parse.steam.entities;

import com.parse.steam.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "washing_machine")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WashingMachineEntity extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "display")
    private Boolean display;

    @Column(name = "depth")
    private Integer depth;

    @Column(name = "vertical_load_type")
    private Boolean verticalLoadType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id", updatable = false, insertable = false)
    private BrandEntity brand;

    @Column(name = "brand_id")
    private Long brandId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wash_tech_id", updatable = false, insertable = false)
    private WashTechEntity washTech;

    @Column(name = "wash_tech_id")
    private Long washTechId;

    @Column(name = "archived")
    private Boolean archived;
}
