package com.parse.steam.entities;

import com.parse.steam.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "monitor_stat")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MonitorStatEntity extends BaseEntity {
    @Column(name = "monitor_market_id")
    private Long monitorMarketId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "monitor_market_id", updatable = false, insertable = false)
    private MonitorMarketEntity monitorMarketEntity;

    @Column(name = "price")
    private Long price;

    @Column(name = "moment")
    private LocalDateTime moment;
}
