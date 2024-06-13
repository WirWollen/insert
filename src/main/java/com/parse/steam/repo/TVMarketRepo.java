package com.parse.steam.repo;

import com.parse.steam.entities.TVMarketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TVMarketRepo extends JpaRepository<TVMarketEntity, Long> {
    @Query(value = "select count(tm.id) from tv_market tm " +
            "where archived = false " +
            "and market_id = :marketId ", nativeQuery = true)
    Long countByMarketId(@Param("marketId") Long marketId);

    @Query(value = "select count(tm.id) from tv_market tm " +
            "where in_redis = true " +
            "and market_id = :marketId ", nativeQuery = true)
    Long countInRedisByMarketId(@Param("marketId") Long marketId);
}
