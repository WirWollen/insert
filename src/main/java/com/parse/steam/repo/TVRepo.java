package com.parse.steam.repo;

import com.parse.steam.entities.TVEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TVRepo extends JpaRepository<TVEntity, Long> {
}
