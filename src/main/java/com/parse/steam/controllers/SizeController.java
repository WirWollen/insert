package com.parse.steam.controllers;

import com.parse.steam.dtos.SizeDto;
import com.parse.steam.services.SizeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/monitor/size")
public class SizeController {
    private final SizeService service;

    @PostMapping("/insert")
    public SizeDto saveSize(@RequestBody SizeDto dto) {
        return service.saveSize(dto);
    }

    @PostMapping("/insert-all")
    public List<SizeDto> saveAllSize(@RequestBody List<SizeDto> dtoList) {
        return service.saveSizeList(dtoList);
    }
}
