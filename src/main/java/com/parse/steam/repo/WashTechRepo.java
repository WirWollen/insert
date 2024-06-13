package com.parse.steam.repo;

import com.parse.steam.entities.WashTechEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface WashTechRepo extends JpaRepository<WashTechEntity, Long> {
    @Query(value = "select * from wash_tech order by name asc", nativeQuery = true)
    List<WashTechEntity> findAllOrderByNameAsc();

    Optional<WashTechEntity> findByName(String name);
}
