package com.parse.steam.repo;

import com.parse.steam.entities.MatrixEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatrixRepo extends JpaRepository<MatrixEntity, Long> {
}
