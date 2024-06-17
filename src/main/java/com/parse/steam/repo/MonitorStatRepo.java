package com.parse.steam.repo;

import com.parse.steam.entities.MonitorStatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MonitorStatRepo extends JpaRepository<MonitorStatEntity, Long> {
    @Query(value = "select ms.* " +
            "from monitor_stat ms " +
            "join monitor_market mm on ms.monitor_market_id = mm.id " +
            "where mm.monitor_id = :itemId " +
            "and mm.market_id = :marketId " +
            "order by moment desc", nativeQuery = true)
    List<MonitorStatEntity> findAllByItemIdAndId(@Param("itemId") Long itemId,
                                                 @Param("marketId") Long marketId);

    @Query(value = "SELECT ms.* " +
            "FROM monitor_stat ms " +
            "JOIN monitor_market mm on mm.id = ms.monitor_market_id " +
            "WHERE mm.market_id = :marketId " +
            "AND ms.moment = (SELECT MAX(moment) FROM monitor_stat " +
            "JOIN monitor_market on monitor_stat.monitor_market_id = monitor_market.id " +
            "WHERE market_id = :marketId) " +
            "AND mm.monitor_id = :monitorId ", nativeQuery = true)
    MonitorStatEntity findLastPrice(@Param("marketId") Long marketId,
                                    @Param("monitorId") Long monitorId);

    @Query(value = "SELECT ms.* " +
            "FROM monitor_stat ms " +
            "JOIN monitor_market mm on mm.id = ms.monitor_market_id " +
            "WHERE mm.market_id = :marketId " +
            "AND ms.price = (SELECT MIN(price) FROM monitor_stat " +
            "JOIN monitor_market on monitor_stat.monitor_market_id = monitor_market.id " +
            "WHERE market_id = :marketId) " +
            "AND mm.monitor_id = :monitorId ", nativeQuery = true)
    MonitorStatEntity findLowestPrice(@Param("marketId") Long marketId,
                                      @Param("monitorId") Long monitorId);

    @Query(value = "SELECT ms.* " +
            "FROM monitor_stat ms " +
            "WHERE ms.moment >= CURRENT_DATE - INTERVAL '1 month' " +
            "and ms.monitor_market_id = :monitorMarketId " +
            "order by ms.moment desc ", nativeQuery = true)
    List<MonitorStatEntity> findByInterval(@Param("monitorMarketId") Long monitorMarketId);
}
