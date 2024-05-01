package com.parse.steam.repo.parsed;

import com.parse.steam.entities.MonitorStatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MonitorStatRepo extends JpaRepository<MonitorStatEntity, Long> {

}
