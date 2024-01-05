package com.parse.steam.entities.central;

import com.parse.steam.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "condition")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConditionEntity extends BaseEntity {
    @Column(name = "condition")
    private String condition;
}
