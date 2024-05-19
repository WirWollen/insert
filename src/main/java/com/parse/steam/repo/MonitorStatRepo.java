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
}
