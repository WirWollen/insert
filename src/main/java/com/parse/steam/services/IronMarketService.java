package com.parse.steam.services;

import com.parse.steam.converters.IronMarketConverter;
import com.parse.steam.dtos.IronMarketDto;
import com.parse.steam.dtos.redis.OuterDto;
import com.parse.steam.dtos.redis.ShopName;
import com.parse.steam.entities.IronMarketEntity;
import com.parse.steam.exceptions.ElementIsArchivedException;
import com.parse.steam.exceptions.ElementNotFoundException;
import com.parse.steam.repo.IronMarketRepo;
import com.parse.steam.repo.redis.RedisRepo;
import com.parse.steam.utils.builders.IronMarketBuilder;
import com.parse.steam.utils.builders.RedisBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IronMarketService {
    private final IronMarketRepo ironMarketRepo;
    private final RedisRepo redisRepo;

    public IronMarketDto saveIronMarket(IronMarketDto dto) {
        return IronMarketConverter.toDto(ironMarketRepo.save(IronMarketConverter.toEntity(dto)));
    }

    public List<IronMarketDto> saveIronMarketList(List<IronMarketDto> dtoList) {
        return ironMarketRepo.saveAll(dtoList.stream().map(IronMarketConverter::toEntity).toList()).stream().map(IronMarketConverter::toDto).toList();
    }

    public IronMarketDto archiveIronMarket(Long id) throws ElementNotFoundException {
        IronMarketEntity entity = ironMarketRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("iron-market not found"));
        entity.setArchived(true);
        return IronMarketConverter.toDto(ironMarketRepo.save(entity));
    }

    public IronMarketDto unarchiveIronMarket(Long id) throws ElementNotFoundException {
        IronMarketEntity entity = ironMarketRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("iron-market not found"));
        entity.setArchived(false);
        return IronMarketConverter.toDto(ironMarketRepo.save(entity));
    }

    public IronMarketDto updateIronMarket(IronMarketDto dto) throws ElementNotFoundException {
        IronMarketEntity entity = ironMarketRepo.findById(dto.getId()).orElseThrow(() -> new ElementNotFoundException("iron-market not found"));
        IronMarketEntity updatedEntity = IronMarketBuilder.buildUpdatedIronMarketEntity(entity, IronMarketConverter.toEntity(dto));
        updatedEntity = ironMarketRepo.save(updatedEntity);
        return IronMarketConverter.toDto(updatedEntity);
    }

    public IronMarketDto getIronMarketById(Long id) throws ElementNotFoundException {
        return IronMarketConverter.toDto(ironMarketRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("iron-market not found")));
    }

    public List<IronMarketDto> getAllIronMarket() {
        return ironMarketRepo.findAll().stream().map(IronMarketConverter::toDto).toList();
    }

    public IronMarketDto insertToRedis(Long id) throws ElementNotFoundException, ElementIsArchivedException {
        IronMarketEntity entity = ironMarketRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("iron-market not found"));
        if (entity.getArchived()) throw new ElementIsArchivedException("элемент архивирован");
        OuterDto redisDto = RedisBuilder.buildRedisDto(entity);
        entity.setInRedis(true);
        redisRepo.save(redisDto);
        return IronMarketConverter.toDto(ironMarketRepo.save(entity));
    }

    public IronMarketDto deleteFromRedis(Long id) throws ElementNotFoundException, ElementIsArchivedException {
        IronMarketEntity entity = ironMarketRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("iron-market not found"));
        if (entity.getArchived()) throw new ElementIsArchivedException("элемент архивирован");
        entity.setInRedis(false);
        redisRepo.delete(ShopName.CITILINK.getName(), id);
        return null;
    }

    public boolean cleanRedis() {
        return redisRepo.cleanRedis();
    }
}
