package com.parse.steam.repo;

import com.parse.steam.entities.ScreenTechEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ScreenTechRepo extends JpaRepository<ScreenTechEntity, Long> {
    @Query(value = "select * from screen_tech order by name asc", nativeQuery = true)
    List<ScreenTechEntity> findAllOrderByNameAsc();

    Optional<ScreenTechEntity> findByName(String name);
}
