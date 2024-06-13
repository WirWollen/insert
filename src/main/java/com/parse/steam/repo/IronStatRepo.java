package com.parse.steam.repo;

import com.parse.steam.entities.IronStatEntity;
import com.parse.steam.entities.MonitorStatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IronStatRepo extends JpaRepository<IronStatEntity, Long> {
    @Query(value = "select irs.* " +
            "from iron_stat irs " +
            "join iron_market irm on irs.iron_market_id = irm.id " +
            "where mm.iron_id = :itemId " +
            "and irm.market_id = :marketId " +
            "order by moment desc", nativeQuery = true)
    List<IronStatEntity> findAllByItemIdAndId(@Param("itemId") Long itemId,
                                              @Param("marketId") Long marketId);

    @Query(value = "SELECT irs.* " +
            "FROM iron_stat irs " +
            "JOIN iron_market irm on irm.id = irs.iron_market_id " +
            "WHERE irm.market_id = :marketId " +
            "AND irs.moment = (SELECT MAX(moment) FROM iron_stat " +
            "JOIN iron_market on iron_stat.iron_market_id = iron_market.id " +
            "WHERE market_id = :marketId) " +
            "AND irm.iron_id = :ironId ", nativeQuery = true)
    IronStatEntity findLastPrice(@Param("marketId") Long marketId,
                                 @Param("ironId") Long ironId);

    @Query(value = "SELECT irs.* " +
            "FROM iron_stat irs " +
            "JOIN iron_market irm on irm.id = irs.iron_market_id " +
            "WHERE irm.market_id = :marketId " +
            "AND irs.price = (SELECT MIN(price) FROM iron_stat " +
            "JOIN iron_market on iron_stat.iron_market_id = iron_market.id " +
            "WHERE market_id = :marketId) " +
            "AND irm.iron_id = :ironId ", nativeQuery = true)
    IronStatEntity findLowestPrice(@Param("marketId") Long marketId,
                                   @Param("ironId") Long ironId);
}
