package com.parse.steam.entities;

import com.parse.steam.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "monitor_market")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MonitorMarketEntity extends BaseEntity {
    @Column(name = "market_id", updatable = false, insertable = false)
    private Long marketId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "market_id")
    private MarketEntity marketEntity;

    @Column(name = "monitor_id", updatable = false, insertable = false)
    private Long monitorId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "monitor_id")
    private MonitorEntity monitorEntity;

    @Column(name = "url")
    private String url;

    @Column(name = "in_redis")
    private Boolean inRedis;

    @Column(name = "archived")
    private Boolean archived;
}
