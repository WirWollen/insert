package com.parse.steam.entities;

import com.parse.steam.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "iron")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IronEntity extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "auto_off")
    private Boolean autoOff;

    @Column(name = "steam_boost")
    private Boolean steamBoost;

    @Column(name = "power")
    private Integer power;

    @Column(name = "auto_clean")
    private Boolean autoClean;

    @Column(name = "anti_drip_system")
    private Boolean antiDripSystem;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id", updatable = false, insertable = false)
    private BrandEntity brand;

    @Column(name = "brand_id")
    private Long brandId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "iron_sole_id", updatable = false, insertable = false)
    private IronSoleEntity ironSole;

    @Column(name = "iron_sole_id")
    private Long ironSoleId;

    @Column(name = "archived")
    private Boolean archived;
}
