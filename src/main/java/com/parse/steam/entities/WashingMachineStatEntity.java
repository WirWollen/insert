package com.parse.steam.entities;

import com.parse.steam.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "washing_machine_stat")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WashingMachineStatEntity extends BaseEntity {
    @Column(name = "washing_machine_market_id", updatable = false, insertable = false)
    private Long washingMachineMarketId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "washing_machine_market_id")
    private WashingMachineMarketEntity washingMachineMarket;

    @Column(name = "price")
    private Long price;

    @Column(name = "moment")
    private LocalDateTime moment;
}
