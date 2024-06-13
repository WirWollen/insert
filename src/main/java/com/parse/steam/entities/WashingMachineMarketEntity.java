package com.parse.steam.entities;

import com.parse.steam.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "washing_machine_market")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WashingMachineMarketEntity extends BaseEntity {
    @Column(name = "market_id")
    private Long marketId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "market_id", updatable = false, insertable = false)
    private MarketEntity marketEntity;

    @Column(name = "washing_machine_id")
    private Long washingMachineId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "washing_machine_id", updatable = false, insertable = false)
    private WashingMachineEntity washingMachine;

    @Column(name = "url")
    private String url;

    @Column(name = "in_redis")
    private Boolean inRedis;

    @Column(name = "archived")
    private Boolean archived;
}
