package com.parse.steam.entities;

import com.parse.steam.entities.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "wash_tech")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WashTechEntity extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "archived")
    private Boolean archived;
}
