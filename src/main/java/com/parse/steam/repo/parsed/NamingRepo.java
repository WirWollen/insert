package com.parse.steam.repo.parsed;

import com.parse.steam.entities.central.NamingEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface NamingRepo extends CrudRepository<NamingEntity, Long> {
    @Query(value = "SELECT EXISTS (SELECT 1 FROM naming WHERE naming_en = ?1)", nativeQuery = true)
    boolean existsByNamingEn(String namingEn);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO naming (naming_en, photo) SELECT ?1, ?2 WHERE NOT EXISTS (SELECT 1 FROM naming WHERE naming_en = ?1)", nativeQuery = true)
        void checkAndInsertNamingEn(String namingEn, String photo);

    @Query(value = "SELECT Id FROM naming WHERE naming_en = ?1 LIMIT 1", nativeQuery = true)
    Integer getIdOfNaming(String naming_en);
}
