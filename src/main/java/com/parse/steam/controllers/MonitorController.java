package com.parse.steam.controllers;

import com.parse.steam.dtos.MonitorDto;
import com.parse.steam.exceptions.ElementNotFoundException;
import com.parse.steam.services.MonitorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/monitor/insert")
public class MonitorController {
    private final MonitorService service;

//    @PostMapping("/monitor-market")
//    public MonitorMarketDto insertMonitorMarket(@RequestBody MonitorMarketDto dto) {
//        return monitorService.insertMonitorMarket(dto);
//    }

    @PostMapping("/save")
    public MonitorDto saveMonitor(@RequestBody MonitorDto dto) {
        return service.saveMonitor(dto);
    }

    @PostMapping("/save-all")
    public List<MonitorDto> saveAllMonitor(@RequestBody List<MonitorDto> dtoList) {
        return service.saveMonitorList(dtoList);
    }

    @PostMapping("/archive")
    public MonitorDto archiveMonitor(@RequestParam Long id) throws ElementNotFoundException {
        return service.archiveMonitor(id);
    }

    @PostMapping("/unarchive")
    public MonitorDto unarchiveMonitor(@RequestParam Long id) throws ElementNotFoundException {
        return service.unarchiveMonitor(id);
    }

    @PostMapping("/update")
    public MonitorDto updateMonitor(@RequestBody MonitorDto dto) throws ElementNotFoundException {
        return service.updateMonitor(dto);
    }

    @GetMapping("/find-by-id")
    public MonitorDto findMonitorById(@RequestParam Long id) throws ElementNotFoundException {
        return service.getMonitorById(id);
    }

    @GetMapping("/get-all")
    public List<MonitorDto> getAllMonitor() {
        return service.getAllMonitor();
    }
}
