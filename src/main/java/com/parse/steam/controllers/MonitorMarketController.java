package com.parse.steam.controllers;

import com.parse.steam.dtos.MarketDto;
import com.parse.steam.dtos.MonitorMarketDto;
import com.parse.steam.exceptions.ElementIsArchivedException;
import com.parse.steam.exceptions.ElementNotFoundException;
import com.parse.steam.services.MonitorMarketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/monitor-market")
public class MonitorMarketController {
    private final MonitorMarketService service;

    @PostMapping("/save")
    public MonitorMarketDto saveMonitorMarket(@RequestBody MonitorMarketDto dto) {
        return service.saveMonitorMarket(dto);
    }

    @PostMapping("/save-all")
    public List<MonitorMarketDto> saveAllMonitorMarket(@RequestBody List<MonitorMarketDto> dtoList) {
        return service.saveMonitorMarketList(dtoList);
    }

    @PostMapping("/archive")
    public MonitorMarketDto archiveMonitorMarket(@RequestParam Long id) throws ElementNotFoundException {
        return service.archiveMonitorMarket(id);
    }

    @PostMapping("/unarchive")
    public MonitorMarketDto unarchiveMonitorMarket(@RequestParam Long id) throws ElementNotFoundException {
        return service.unarchiveMonitorMarket(id);
    }

    @PostMapping("/update")
    public MonitorMarketDto updateMonitorMarket(@RequestBody MonitorMarketDto dto) throws ElementNotFoundException {
        return service.updateMonitorMarket(dto);
    }

    @GetMapping("/find-by-id")
    public MonitorMarketDto findMonitorMarketById(@RequestParam Long id) throws ElementNotFoundException {
        return service.getMonitorMarketById(id);
    }

    @GetMapping("/get-all")
    public List<MonitorMarketDto> getAllMonitorMarket() {
        return service.getAllMonitorMarket();
    }

    @GetMapping("/insert-to-redis")
    public MonitorMarketDto insertToRedis(@RequestParam Long id) throws ElementNotFoundException, ElementIsArchivedException {
        return service.insertToRedis(id);
    }

    @GetMapping("/delete-from-redis")
    public MonitorMarketDto deleteFromRedis(@RequestParam Long id) throws ElementNotFoundException, ElementIsArchivedException {
        return service.deleteFromRedis(id);
    }
}
