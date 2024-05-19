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
            "and mm.monitor_id = :marketId ", nativeQuery = true)
    List<MonitorStatEntity> findAllByItemIdAndId(@Param("itemId") Long itemId,
                                                 @Param("marketId") Long marketId);

    @Query(value = "SELECT ms.* " +
            "FROM monitor_stat ms " +
            "JOIN monitor_market mm on mm.id = ms.monitor_market_id " +
            "WHERE ms.monitor_market_id = :monitorMarketId " +
            "AND ms.moment = (SELECT MAX(moment) FROM monitor_stat WHERE monitor_market_id = :monitorMarketId) " +
            "AND mm.monitor_id = :monitorId ", nativeQuery = true)
    MonitorStatEntity findLastPrice(@Param("monitorMarketId") Long monitorMarketId,
                                    @Param("monitorId") Long monitorId);

    @Query(value = "SELECT ms.* " +
            "FROM monitor_stat ms " +
            "JOIN monitor_market mm on mm.id = ms.monitor_market_id " +
            "WHERE ms.monitor_market_id = :monitorMarketId " +
            "AND ms.price = (SELECT MIN(price) FROM monitor_stat WHERE monitor_market_id = :monitorMarketId) " +
            "AND mm.monitor_id = :monitorId ", nativeQuery = true)
    MonitorStatEntity findLowestPrice(@Param("monitorMarketId") Long monitorMarketId,
                                      @Param("monitorId") Long monitorId);
}
