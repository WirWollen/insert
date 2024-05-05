package com.parse.steam.repo;

import com.parse.steam.entities.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BrandRepo extends JpaRepository<BrandEntity, Long> {

    @Query(value = "select * from brand order by name asc", nativeQuery = true)
    List<BrandEntity> findAllOrderByNameAsc();
}
