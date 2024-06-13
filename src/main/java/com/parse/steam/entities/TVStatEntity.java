package com.parse.steam.entities;

import com.parse.steam.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "tv_stat")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TVStatEntity extends BaseEntity {
    @Column(name = "tv_market_id", updatable = false, insertable = false)
    private Long tvMarketId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tv_market_id")
    private TVMarketEntity tvMarket;

    @Column(name = "price")
    private Long price;

    @Column(name = "moment")
    private LocalDateTime moment;
}
