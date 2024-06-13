package com.parse.steam.repo;

import com.parse.steam.entities.WashingMachineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WashingMachineRepo extends JpaRepository<WashingMachineEntity, Long> {
}
