package com.parse.steam.controllers;

import com.parse.steam.dtos.SizeDto;
import com.parse.steam.exceptions.ElementNotFoundException;
import com.parse.steam.services.SizeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://locathost:4200")
@AllArgsConstructor
@RestController
@RequestMapping("/size")
public class SizeController {
    private final SizeService service;

    @PostMapping("/save")
    public SizeDto saveSize(@RequestBody SizeDto dto) {
        return service.saveSize(dto);
    }

    @PostMapping("/save-all")
    public List<SizeDto> saveAllSize(@RequestBody List<SizeDto> dtoList) {
        return service.saveSizeList(dtoList);
    }

    @PostMapping("/archive")
    public SizeDto archiveSize(@RequestParam Long id) throws ElementNotFoundException {
        return service.archiveSize(id);
    }

    @PostMapping("/unarchive")
    public SizeDto unarchiveSize(@RequestParam Long id) throws ElementNotFoundException {
        return service.unarchiveSize(id);
    }

    @PostMapping("/update")
    public SizeDto updateSize(@RequestBody SizeDto dto) throws ElementNotFoundException {
        return service.updateSize(dto);
    }

    @GetMapping("/find-by-id")
    public SizeDto findSizeById(@RequestParam Long id) throws ElementNotFoundException {
        return service.getSizeById(id);
    }

    @GetMapping("/get-all")
    public List<SizeDto> getAllSize() {
        return service.getAllSize();
    }
}
