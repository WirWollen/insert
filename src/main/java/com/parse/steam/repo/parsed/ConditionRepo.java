package com.parse.steam.repo.parsed;

import com.parse.steam.entities.central.ConditionEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ConditionRepo extends CrudRepository<ConditionEntity, Long> {
    @Query(value = "SELECT EXISTS (SELECT 1 FROM condition WHERE condition.condition = ?1)", nativeQuery = true)
    boolean existsByCondition(String condition);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO condition (condition) SELECT ?1 WHERE NOT EXISTS (SELECT 1 FROM condition WHERE condition = ?1)", nativeQuery = true)
    void checkAndInsertCondition(String condition);

    @Query(value = "SELECT Id FROM condition WHERE condition = ?1 LIMIT 1", nativeQuery = true)
    Integer getIdOfCondition(String condition);
}
