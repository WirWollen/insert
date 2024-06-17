package com.parse.steam.repo;

import com.parse.steam.entities.MonitorMarketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MonitorMarketRepo extends JpaRepository<MonitorMarketEntity, Long> {
    @Query(value = "select count(mm.id) from monitor_market mm " +
            "where archived = false " +
            "and market_id = :marketId ", nativeQuery = true)
    Long countByMarketId(@Param("marketId") Long marketId);

    @Query(value = "select count(mm.id) from monitor_market mm " +
            "where in_redis = true " +
            "and market_id = :marketId ", nativeQuery = true)
    Long countInRedisByMarketId(@Param("marketId") Long marketId);

    List<MonitorMarketEntity> findAllByMonitorId(Long monitorId);
}
