package com.parse.steam.repo;

import com.parse.steam.entities.MonitorStatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonitorStatRepo extends JpaRepository<MonitorStatEntity, Long> {
}
