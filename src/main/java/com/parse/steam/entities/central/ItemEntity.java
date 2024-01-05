package com.parse.steam.entities.central;

import com.parse.steam.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "item")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemEntity extends BaseEntity {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "condition_id")
    private ConditionEntity condition;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "weapon_type_id")
    private WeaponTypeEntity weaponType;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "naming_id")
    private NamingEntity naming;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "st")
    private Boolean st;
    @Column(name = "souvenir")
    private Boolean souvenir;
}
