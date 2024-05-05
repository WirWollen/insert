package com.parse.steam.entities;

import com.parse.steam.entities.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "monitor_size")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SizeEntity extends BaseEntity {
    @Column(name = "size_x")
    private Integer sizeX;
    @Column(name = "size_y")
    private Integer sizeY;
    @Column(name = "archived")
    private Boolean archived;
}
