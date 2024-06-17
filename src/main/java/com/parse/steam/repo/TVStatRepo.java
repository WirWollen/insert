package com.parse.steam.repo;

import com.parse.steam.entities.TVStatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TVStatRepo extends JpaRepository<TVStatEntity, Long> {
    @Query(value = "select ts.* " +
            "from tv_stat ts " +
            "join tv_market tm on ts.tv_market_id = tm.id " +
            "where tm.tv_id = :itemId " +
            "and tm.market_id = :marketId " +
            "order by moment desc", nativeQuery = true)
    List<TVStatEntity> findAllByItemIdAndId(@Param("itemId") Long itemId,
                                            @Param("marketId") Long marketId);

    @Query(value = "SELECT ts.* " +
            "FROM tv_stat ts " +
            "JOIN tv_market tm on tm.id = ts.tv_market_id " +
            "WHERE tm.market_id = :marketId " +
            "AND ts.moment = (SELECT MAX(moment) FROM tv_stat " +
            "JOIN tv_market on tv_stat.tv_market_id = tv_market.id " +
            "WHERE market_id = :marketId) " +
            "AND tm.tv_id = :tvId ", nativeQuery = true)
    TVStatEntity findLastPrice(@Param("marketId") Long marketId,
                               @Param("tvId") Long tvId);

    @Query(value = "SELECT ts.* " +
            "FROM tv_stat ts " +
            "JOIN tv_market tm on tm.id = ts.tv_market_id " +
            "WHERE tm.market_id = :marketId " +
            "AND ts.price = (SELECT MIN(price) FROM tv_stat " +
            "JOIN tv_market on tv_stat.tv_market_id = tv_market.id " +
            "WHERE market_id = :marketId) " +
            "AND tm.tv_id = :tvId ", nativeQuery = true)
    TVStatEntity findLowestPrice(@Param("marketId") Long marketId,
                                 @Param("tvId") Long tvId);

    @Query(value = "SELECT ms.* " +
            "FROM tv_stat ms " +
            "WHERE ms.moment >= CURRENT_DATE - INTERVAL '1 month' " +
            "and ms.tv_market_id = :tvMarketId " +
            "order by ms.moment desc ", nativeQuery = true)
    List<TVStatEntity> findByInterval(@Param("tvMarketId") Long tvMarketId);
}
