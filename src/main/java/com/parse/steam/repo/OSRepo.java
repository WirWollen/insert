package com.parse.steam.repo;

import com.parse.steam.entities.OsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OSRepo extends JpaRepository<OsEntity, Long> {
    @Query(value = "select * from os order by name asc", nativeQuery = true)
    List<OsEntity> findAllOrderByNameAsc();

    Optional<OsEntity> findByName(String name);
}
