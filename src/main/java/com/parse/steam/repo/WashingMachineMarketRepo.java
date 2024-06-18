package com.parse.steam.repo;

import com.parse.steam.entities.WashingMachineMarketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WashingMachineMarketRepo extends JpaRepository<WashingMachineMarketEntity, Long> {
    @Query(value = "select count(wmm.id) from washing_machine_market wmm " +
            "where archived = false " +
            "and market_id = :marketId ", nativeQuery = true)
    Long countByMarketId(@Param("marketId") Long marketId);

    @Query(value = "select count(wmm.id) from washing_machine_market wmm " +
            "where in_redis = true " +
            "and market_id = :marketId ", nativeQuery = true)
    Long countInRedisByMarketId(@Param("marketId") Long marketId);

    List<WashingMachineMarketEntity> findAllByWashingMachineId(Long washingMachineId);
}
