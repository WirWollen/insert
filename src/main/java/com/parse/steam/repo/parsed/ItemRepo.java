package com.parse.steam.repo.parsed;

import com.parse.steam.entities.central.ItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ItemRepo extends CrudRepository<ItemEntity, Long> {
    List<ItemEntity> findAll();
    Page<ItemEntity> findAll(Pageable pageable);

    @Query(value = "SELECT EXISTS (SELECT 1 " +
            "FROM item " +
            "JOIN condition ON item.condition_id = condition.id " +
            "JOIN weapon_type ON item.weapon_type_id = weapon_type.id " +
            "JOIN naming ON item.naming_id = naming.id " +
            "WHERE condition.condition = ?1 AND weapon_type.type = ?2 AND naming.naming_en = ?3 " +
            "LIMIT 1)", nativeQuery = true)
    Boolean findByParams(String condition, String weaponType, String namingEn);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO item (condition_id, weapon_type_id, naming_id, active, st, souvenir) VALUES (?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery = true)
    void saveByParams(Integer conditionId, Integer weaponTypeId, Integer namingId, Boolean active, Boolean st, Boolean souvenir);

}
