package com.parse.steam.controllers;

import com.parse.steam.dtos.WashingMachineMarketDto;
import com.parse.steam.exceptions.ElementIsArchivedException;
import com.parse.steam.exceptions.ElementNotFoundException;
import com.parse.steam.services.WashingMachineMarketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://locathost:4200")
@AllArgsConstructor
@RestController
@RequestMapping("/washing-machine-market")
public class WashingMachineMarketController {
    private final WashingMachineMarketService service;

    @PostMapping("/save")
    public WashingMachineMarketDto saveMonitorMarket(@RequestBody WashingMachineMarketDto dto) {
        return service.saveWashingMachineMarket(dto);
    }

    @PostMapping("/save-all")
    public List<WashingMachineMarketDto> saveAllMonitorMarket(@RequestBody List<WashingMachineMarketDto> dtoList) {
        return service.saveWashingMachineMarketList(dtoList);
    }

    @PostMapping("/archive")
    public WashingMachineMarketDto archiveMonitorMarket(@RequestParam Long id) throws ElementNotFoundException {
        return service.archiveWashingMachineMarket(id);
    }

    @PostMapping("/unarchive")
    public WashingMachineMarketDto unarchiveMonitorMarket(@RequestParam Long id) throws ElementNotFoundException {
        return service.unarchiveWashingMachineMarket(id);
    }

    @PostMapping("/update")
    public WashingMachineMarketDto updateMonitorMarket(@RequestBody WashingMachineMarketDto dto) throws ElementNotFoundException {
        return service.updateWashingMachineMarket(dto);
    }

    @GetMapping("/find-by-id")
    public WashingMachineMarketDto findMonitorMarketById(@RequestParam Long id) throws ElementNotFoundException {
        return service.getWashingMachineMarketById(id);
    }

    @GetMapping("/get-all")
    public List<WashingMachineMarketDto> getAllMonitorMarket() {
        return service.getAllWashingMachineMarket();
    }

    @GetMapping("/insert-to-redis")
    public WashingMachineMarketDto insertToRedis(@RequestParam Long id) throws ElementNotFoundException, ElementIsArchivedException {
        return service.insertToRedis(id);
    }

    @GetMapping("/delete-from-redis")
    public WashingMachineMarketDto deleteFromRedis(@RequestParam Long id) throws ElementNotFoundException, ElementIsArchivedException {
        return service.deleteFromRedis(id);
    }

    @GetMapping("/clean-redis")
    public boolean cleanRedis() {
        return service.cleanRedis();
    }
}
