package com.parse.steam.controllers;

import com.parse.steam.dtos.MonitorDto;
import com.parse.steam.services.InsertModelService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/monitor")
public class InsertModelController {
    private final InsertModelService insertModelService;

    @PostMapping("/insert")
    public MonitorDto insertMonitor(@RequestBody MonitorDto dto) {
        return insertModelService.insertMonitor(dto);
    }

    @PostMapping("/insert-all")
    public List<MonitorDto> insertMonitorList(@RequestBody List<MonitorDto> dtoList) {
        return insertModelService.insertMonitorList(dtoList);
    }
}
