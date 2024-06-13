package com.parse.steam.entities;

import com.parse.steam.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "iron_market")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IronMarketEntity extends BaseEntity {
    @Column(name = "market_id")
    private Long marketId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "market_id", updatable = false, insertable = false)
    private MarketEntity market;

    @Column(name = "iron_id")
    private Long ironId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "iron_id", updatable = false, insertable = false)
    private IronEntity iron;

    @Column(name = "url")
    private String url;

    @Column(name = "in_redis")
    private Boolean inRedis;

    @Column(name = "archived")
    private Boolean archived;
}
