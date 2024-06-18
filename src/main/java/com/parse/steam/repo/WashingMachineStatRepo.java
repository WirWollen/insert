package com.parse.steam.repo;

import com.parse.steam.entities.WashingMachineStatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WashingMachineStatRepo extends JpaRepository<WashingMachineStatEntity, Long> {
    @Query(value = "select wms.* " +
            "from washing_machine_stat wms " +
            "join washing_machine_market wmm on wms.washing_machine_market_id = wmm.id " +
            "where wmm.washing_machine_id = :itemId " +
            "and wmm.market_id = :marketId " +
            "order by moment desc", nativeQuery = true)
    List<WashingMachineStatEntity> findAllByItemIdAndId(@Param("itemId") Long itemId,
                                                        @Param("marketId") Long marketId);

    @Query(value = "SELECT wms.* " +
            "FROM washing_machine_stat wms " +
            "JOIN washing_machine_market wmm on wmm.id = wms.washing_machine_market_id " +
            "WHERE wmm.market_id = :marketId " +
            "AND wms.moment = (SELECT MAX(moment) FROM washing_machine_stat " +
            "JOIN washing_machine_market on washing_machine_stat.washing_machine_market_id = washing_machine_market.id " +
            "WHERE market_id = :marketId) " +
            "AND wmm.washing_machine_id = :washingMachineId ", nativeQuery = true)
    WashingMachineStatEntity findLastPrice(@Param("marketId") Long marketId,
                                           @Param("washingMachineId") Long washingMachineId);

    @Query(value = "SELECT wms.* " +
            "FROM washing_machine_stat wms " +
            "JOIN washing_machine_market wmm on wmm.id = wms.washing_machine_market_id " +
            "WHERE wmm.market_id = :marketId " +
            "AND wms.price = (SELECT MIN(price) FROM washing_machine_stat " +
            "JOIN washing_machine_market on washing_machine_stat.washing_machine_market_id = washing_machine_market.id " +
            "WHERE market_id = :marketId) " +
            "AND wmm.washing_machine_id = :washingMachineId ", nativeQuery = true)
    WashingMachineStatEntity findLowestPrice(@Param("marketId") Long marketId,
                                             @Param("washingMachineId") Long washingMachineId);

    @Query(value = "SELECT ms.* " +
            "FROM washing_machine_stat ms " +
            "WHERE ms.moment >= CURRENT_DATE - INTERVAL '1 month' " +
            "and ms.washing_machine_market_id = :washingMachineMarketId " +
            "order by ms.moment desc ", nativeQuery = true)
    List<WashingMachineStatEntity> findByInterval(@Param("washingMachineMarketId") Long washingMachineMarketId);
}
