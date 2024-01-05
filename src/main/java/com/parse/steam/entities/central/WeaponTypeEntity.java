package com.parse.steam.entities.central;

import com.parse.steam.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "weapon_type")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeaponTypeEntity extends BaseEntity {
    @Column(name = "type")
    private String type;
}
