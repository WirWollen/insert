package com.parse.steam.services;

import com.parse.steam.converters.TVMarketConverter;
import com.parse.steam.dtos.MonitorMarketDto;
import com.parse.steam.dtos.TVMarketDto;
import com.parse.steam.dtos.redis.OuterDto;
import com.parse.steam.dtos.redis.ShopName;
import com.parse.steam.entities.MonitorMarketEntity;
import com.parse.steam.entities.TVMarketEntity;
import com.parse.steam.exceptions.ElementIsArchivedException;
import com.parse.steam.exceptions.ElementNotFoundException;
import com.parse.steam.repo.TVMarketRepo;
import com.parse.steam.repo.redis.RedisRepo;
import com.parse.steam.utils.builders.MonitorMarketBuilder;
import com.parse.steam.utils.builders.RedisBuilder;
import com.parse.steam.utils.builders.TVMarketBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TVMarketService {
    private final TVMarketRepo tvMarketRepo;
    private final RedisRepo redisRepo;

    public TVMarketDto saveTVMarket(TVMarketDto dto) {
        return TVMarketConverter.toDto(tvMarketRepo.save(TVMarketConverter.toEntity(dto)));
    }

    public List<TVMarketDto> saveTVMarketList(List<TVMarketDto> dtoList) {
        return tvMarketRepo.saveAll(dtoList.stream().map(TVMarketConverter::toEntity).toList()).stream().map(TVMarketConverter::toDto).toList();
    }

    public TVMarketDto archiveTVMarket(Long id) throws ElementNotFoundException {
        TVMarketEntity entity = tvMarketRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("tv-market not found"));
        entity.setArchived(true);
        return TVMarketConverter.toDto(tvMarketRepo.save(entity));
    }

    public TVMarketDto unarchiveTVMarket(Long id) throws ElementNotFoundException {
        TVMarketEntity entity = tvMarketRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("tv-market not found"));
        entity.setArchived(false);
        return TVMarketConverter.toDto(tvMarketRepo.save(entity));
    }

    public TVMarketDto updateTVMarket(TVMarketDto dto) throws ElementNotFoundException {
        TVMarketEntity entity = tvMarketRepo.findById(dto.getId()).orElseThrow(() -> new ElementNotFoundException("tv-market not found"));
        TVMarketEntity updatedEntity = TVMarketBuilder.buildUpdatedTVMarketEntity(entity, TVMarketConverter.toEntity(dto));
        updatedEntity = tvMarketRepo.save(updatedEntity);
        return TVMarketConverter.toDto(updatedEntity);
    }

    public TVMarketDto getTVMarketById(Long id) throws ElementNotFoundException {
        return TVMarketConverter.toDto(tvMarketRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("tv-market not found")));
    }

    public List<TVMarketDto> getAllTVMarket() {
        return tvMarketRepo.findAll().stream().map(TVMarketConverter::toDto).toList();
    }

    public TVMarketDto insertToRedis(Long id) throws ElementNotFoundException, ElementIsArchivedException {
        TVMarketEntity entity = tvMarketRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("tv-market not found"));
        if (entity.getArchived()) throw new ElementIsArchivedException("элемент архивирован");
        OuterDto redisDto = RedisBuilder.buildRedisDto(entity);
        entity.setInRedis(true);
        redisRepo.save(redisDto);
        return TVMarketConverter.toDto(tvMarketRepo.save(entity));
    }

    public TVMarketDto deleteFromRedis(Long id) throws ElementNotFoundException, ElementIsArchivedException {
        TVMarketEntity entity = tvMarketRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("tv-market not found"));
        if (entity.getArchived()) throw new ElementIsArchivedException("элемент архивирован");
        entity.setInRedis(false);
        redisRepo.delete(ShopName.CITILINK.getName(), id);
        return null;
    }

    public boolean cleanRedis() {
        return redisRepo.cleanRedis();
    }
}
