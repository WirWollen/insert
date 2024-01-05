package com.parse.steam.repo.parsed;

import com.parse.steam.entities.central.WeaponTypeEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface WeaponTypeRepo extends CrudRepository<WeaponTypeEntity, Long> {
    @Query(value = "SELECT EXISTS (SELECT 1 FROM weapon_type WHERE type = ?1)", nativeQuery = true)
    boolean existsByType(String type);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO weapon_type (type) SELECT ?1 WHERE NOT EXISTS (SELECT 1 FROM weapon_type WHERE type = ?1)", nativeQuery = true)
    void checkAndInsertWeaponType(String condition);

    @Query(value = "SELECT Id FROM weapon_type WHERE type = ?1 LIMIT 1", nativeQuery = true)
    Integer getIdOfWeaponType(String weaponType);
}
