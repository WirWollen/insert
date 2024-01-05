package com.parse.steam.config;

import com.parse.steam.entities.central.ConditionEntity;
import com.parse.steam.entities.central.WeaponTypeEntity;
import com.parse.steam.repo.parsed.ConditionRepo;
import com.parse.steam.repo.parsed.WeaponTypeRepo;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Configuration
public class CacheBean {
    private final ConditionRepo conditionRepo;
    private final WeaponTypeRepo weaponTypeRepo;

    @Bean
    public Set<String> hashCondition() {
        Set<ConditionEntity> conditionsSet = new HashSet<>();

        for (ConditionEntity condition : conditionRepo.findAll()) {
            conditionsSet.add(condition);
        }
        return conditionsSet.stream().map(ConditionEntity::getCondition).collect(Collectors.toSet());
    }

    @Bean
    public Set<String> hashWeaponType() {
        Set<WeaponTypeEntity> weaponTypeSet = new HashSet<>();

        for (WeaponTypeEntity type : weaponTypeRepo.findAll()) {
            weaponTypeSet.add(type);
        }
        return weaponTypeSet.stream().map(WeaponTypeEntity::getType).collect(Collectors.toSet());
    }
}
