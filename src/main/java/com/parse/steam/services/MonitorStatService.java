package com.parse.steam.services;

import com.parse.steam.converters.MarketConverter;
import com.parse.steam.converters.MonitorStatConverter;
import com.parse.steam.dtos.stat.StatElDto;
import com.parse.steam.repo.MarketRepo;
import com.parse.steam.repo.MonitorStatRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MonitorStatService {
    private final MonitorStatRepo monitorStatRepo;
    private final MarketRepo marketRepo;

    public List<StatElDto> findStatByMonitorId(Long itemId) {
        return marketRepo.findAll().stream().map(MarketConverter::toDto)
                .toList().stream()
                .map(el -> new StatElDto(el.getName(), monitorStatRepo.findAllByItemIdAndId(itemId, el.getId())
                .stream().map(MonitorStatConverter::toDto).toList())).toList();
    }
}
