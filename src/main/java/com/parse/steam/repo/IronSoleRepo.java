package com.parse.steam.repo;

import com.parse.steam.entities.IronSoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IronSoleRepo extends JpaRepository<IronSoleEntity, Long> {
    @Query(value = "select * from iron_sole order by name asc", nativeQuery = true)
    List<IronSoleEntity> findAllOrderByNameAsc();

    Optional<IronSoleEntity> findByName(String name);
}
