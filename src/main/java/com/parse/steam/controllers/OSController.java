package com.parse.steam.controllers;

import com.parse.steam.dtos.OsDto;
import com.parse.steam.exceptions.ElementNotFoundException;
import com.parse.steam.services.OsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://locathost:3000")
@AllArgsConstructor
@RestController
@RequestMapping("/os")
public class OSController {
    private final OsService service;

    @PostMapping("/save")
    public OsDto saveOS(@RequestBody OsDto dto) {
        return service.saveOs(dto);
    }

    @PostMapping("/save-all")
    public List<OsDto> saveAllOS(@RequestBody List<OsDto> dtoList) {
        return service.saveOsList(dtoList);
    }

    @PostMapping("/archive")
    public OsDto archiveOS(@RequestParam Long id) throws ElementNotFoundException {
        return service.archiveOs(id);
    }

    @PostMapping("/unarchive")
    public OsDto unarchiveOS(@RequestParam Long id) throws ElementNotFoundException {
        return service.unarchiveOs(id);
    }

    @PostMapping("/update")
    public OsDto updateOS(@RequestBody OsDto dto) throws ElementNotFoundException {
        return service.updateOs(dto);
    }

    @GetMapping("/find-by-id")
    public OsDto findBrandById(@RequestParam Long id) throws ElementNotFoundException {
        return service.getOsById(id);
    }

    @CrossOrigin(origins = "http://locathost:3000")
    @GetMapping("/get-all")
    public List<OsDto> getAllBrand() {
        return service.getAllOs();
    }

    @PostMapping("/get-by-name")
    public OsDto getBrandByName(@RequestParam String name) throws ElementNotFoundException {
        return service.getOsByName(name);
    }
}
