package com.parse.steam.services;

import com.parse.steam.config.CacheBean;
import com.parse.steam.dtos.central.ItemDto;
import com.parse.steam.repo.parsed.ConditionRepo;
import com.parse.steam.repo.parsed.ItemRepo;
import com.parse.steam.repo.parsed.NamingRepo;
import com.parse.steam.repo.parsed.WeaponTypeRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateDBService {
    @Value("${steam.all-cs-item-url}")
    private String url;
    @Autowired
    private CacheBean cacheBean;
    private final ItemRepo itemRepo;
    private final NamingRepo namingRepo;
    private final ConditionRepo conditionRepo;
    private final WeaponTypeRepo weaponTypeRepo;

    @Transactional
    public void insert(ItemDto dto) {
        if (!namingRepo.existsByNamingEn(dto.getNamingDto().getNaming_en())) {
            namingRepo.checkAndInsertNamingEn(dto.getNamingDto().getNaming_en(), dto.getNamingDto().getPhoto());
        }
        if (!(cacheBean.hashCondition().contains(dto.getConditionDto().getCondition()) || conditionRepo.existsByCondition(dto.getConditionDto().getCondition()))) {
            conditionRepo.checkAndInsertCondition(dto.getConditionDto().getCondition());
        }
        if (!(cacheBean.hashWeaponType().contains(dto.getWeaponTypeDto().getType()) || weaponTypeRepo.existsByType(dto.getWeaponTypeDto().getType()))) {
            weaponTypeRepo.checkAndInsertWeaponType(dto.getWeaponTypeDto().getType());
        }
        if (!itemRepo.findByParams(dto.getConditionDto().getCondition(), dto.getWeaponTypeDto().getType(), dto.getNamingDto().getNaming_en())) {
            itemRepo.saveByParams(
                    conditionRepo.getIdOfCondition(dto.getConditionDto().getCondition()),
                    weaponTypeRepo.getIdOfWeaponType(dto.getWeaponTypeDto().getType()),
                    namingRepo.getIdOfNaming(dto.getNamingDto().getNaming_en()),
                    dto.getActive(),
                    dto.getSt(),
                    dto.getSouvenir()
            );
        }
    }

}
