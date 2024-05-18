package com.parse.steam.services;

import com.parse.steam.converters.MarketConverter;
import com.parse.steam.dtos.MarketDto;
import com.parse.steam.entities.MarketEntity;
import com.parse.steam.exceptions.ElementNotFoundException;
import com.parse.steam.repo.MarketRepo;
import com.parse.steam.utils.builders.MarketBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MarketService {
    private final MarketRepo marketRepo;

    public MarketDto saveMarket(MarketDto dto) {
        dto.setGuid(UUID.randomUUID().toString());
        return MarketConverter.toDto(marketRepo.save(MarketConverter.toEntity(dto)));
    }

    public List<MarketDto> saveMarketList(List<MarketDto> dtoList) {
        return marketRepo.saveAll(dtoList.stream().map(MarketConverter::toEntity).toList()).stream().map(MarketConverter::toDto).toList();
    }

    public MarketDto archiveMarket(Long id) throws ElementNotFoundException {
        MarketEntity entity = marketRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("brand not found"));
        entity.setArchived(true);
        return MarketConverter.toDto(marketRepo.save(entity));
    }

    public MarketDto unarchiveMarket(Long id) throws ElementNotFoundException {
        MarketEntity entity = marketRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("brand not found"));
        entity.setArchived(false);
        return MarketConverter.toDto(marketRepo.save(entity));
    }

    public MarketDto updateMarket(MarketDto dto) throws ElementNotFoundException {
        MarketEntity entity = marketRepo.findById(dto.getId()).orElseThrow(() -> new ElementNotFoundException("brand not found"));
        MarketEntity updatedEntity = MarketBuilder.buildUpdatedMarketEntity(entity, MarketConverter.toEntity(dto));
        updatedEntity = marketRepo.save(updatedEntity);
        return MarketConverter.toDto(updatedEntity);
    }

    public MarketDto getMarketById(Long id) throws ElementNotFoundException {
        return MarketConverter.toDto(marketRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("brand not found")));
    }

    public MarketDto getMarketByName(String name) throws ElementNotFoundException {
        return MarketConverter.toDto(marketRepo.findByName(name).orElseThrow(() -> new ElementNotFoundException("brand not found")));
    }

    public List<MarketDto> getAllMarket() {
        return marketRepo.findAll().stream().map(MarketConverter::toDto).toList();
    }
}
