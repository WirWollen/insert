package com.parse.steam.controllers;

import com.parse.steam.dtos.stat.StatElDto;
import com.parse.steam.services.MonitorStatService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://locathost:4200")
@AllArgsConstructor
@RestController
@RequestMapping("/monitor-stat")
public class MonitorStatController {
    private final MonitorStatService service;

    @PostMapping("/get-by-item-id")
    public List<StatElDto> saveMonitor(@RequestParam Long itemId) {
        return service.findStatByMonitorId(itemId);
    }


}
