package com.parse.steam.repo;

import com.parse.steam.entities.MarketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketRepo extends JpaRepository<MarketEntity, Long> {

}
