package com.parse.steam.repo;

import com.parse.steam.entities.MarketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MarketRepo extends JpaRepository<MarketEntity, Long> {
    Optional<MarketEntity> findByName(String name);
}
