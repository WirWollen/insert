package com.parse.steam.controllers;

import com.parse.steam.dtos.MonitorDto;
import com.parse.steam.dtos.MonitorMarketDto;
import com.parse.steam.services.MonitorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/monitor/insert")
public class InsertModelController {
    private final MonitorService monitorService;

    @PostMapping("/monitor")
    public MonitorDto insertMonitor(@RequestBody MonitorDto dto) {
        return monitorService.insertMonitor(dto);
    }

//    @PostMapping("/monitor-market")
//    public MonitorMarketDto insertMonitorMarket(@RequestBody MonitorMarketDto dto) {
//        return monitorService.insertMonitorMarket(dto);
//    }

    @PostMapping("/insert-all")
    public List<MonitorDto> insertMonitorList(@RequestBody List<MonitorDto> dtoList) {
        return monitorService.insertMonitorList(dtoList);
    }
}
