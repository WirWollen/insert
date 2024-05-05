package com.parse.steam.repo;

import com.parse.steam.entities.MonitorMarketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonitorMarketRepo extends JpaRepository<MonitorMarketEntity, Long> {
}
