package com.parse.steam.repo;

import com.parse.steam.entities.MonitorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonitorRepo extends JpaRepository<MonitorEntity, Long> {
}
