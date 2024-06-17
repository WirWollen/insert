package com.parse.steam.repo;

import com.parse.steam.entities.MarketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MarketRepo extends JpaRepository<MarketEntity, Long> {
    Optional<MarketEntity> findByName(String name);

    @Query(value = "select m.* from market m where m.archived is false", nativeQuery = true)
    List<MarketEntity> findAllActiveMarkets();
}
