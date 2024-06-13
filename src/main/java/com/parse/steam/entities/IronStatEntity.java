package com.parse.steam.entities;

import com.parse.steam.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "iron_stat")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IronStatEntity extends BaseEntity {
    @Column(name = "iron_market_id", updatable = false, insertable = false)
    private Long ironMarketId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "iron_market_id")
    private IronMarketEntity ironMarketEntity;

    @Column(name = "price")
    private Long price;

    @Column(name = "moment")
    private LocalDateTime moment;
}
