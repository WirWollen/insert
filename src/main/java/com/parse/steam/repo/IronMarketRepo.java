package com.parse.steam.repo;

import com.parse.steam.entities.IronMarketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IronMarketRepo extends JpaRepository<IronMarketEntity, Long> {
    @Query(value = "select count(im.id) from iron_market im " +
            "where archived = false " +
            "and market_id = :marketId ", nativeQuery = true)
    Long countByMarketId(@Param("marketId") Long marketId);

    @Query(value = "select count(im.id) from iron_market im " +
            "where in_redis = true " +
            "and market_id = :marketId ", nativeQuery = true)
    Long countInRedisByMarketId(@Param("marketId") Long marketId);

    List<IronMarketEntity> findAllByIronId(Long ironId);
}
