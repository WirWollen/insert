package com.parse.steam.entities;

import com.parse.steam.entities.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "market")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MarketEntity extends BaseEntity {
    @Column(name = "guid")
    private String guid;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    @Column(name = "archived")
    private Boolean archived;
}
