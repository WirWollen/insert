package com.parse.steam.entities.central;

import com.parse.steam.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "naming")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NamingEntity extends BaseEntity {
    @Column(name = "naming_rus")
    private String naming_rus;
    @Column(name = "naming_en")
    private String naming_en;
    @Column(name = "photo")
    private String photo;
}
