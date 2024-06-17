package com.parse.steam.controllers;

import com.parse.steam.dtos.ScreenTechDto;
import com.parse.steam.exceptions.ElementNotFoundException;
import com.parse.steam.services.ScreenTechService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://locathost:3000")
@AllArgsConstructor
@RestController
@RequestMapping("/screen-tech")
public class ScreenTechController {
    private final ScreenTechService service;

    @PostMapping("/save")
    public ScreenTechDto saveBrand(@RequestBody ScreenTechDto dto) {
        return service.saveScreenTech(dto);
    }

    @PostMapping("/save-all")
    public List<ScreenTechDto> saveAllBrand(@RequestBody List<ScreenTechDto> dtoList) {
        return service.saveScreenTechList(dtoList);
    }

    @PostMapping("/archive")
    public ScreenTechDto archiveBrand(@RequestParam Long id) throws ElementNotFoundException {
        return service.archiveScreenTech(id);
    }

    @PostMapping("/unarchive")
    public ScreenTechDto unarchiveBrand(@RequestParam Long id) throws ElementNotFoundException {
        return service.unarchiveScreenTech(id);
    }

    @PostMapping("/update")
    public ScreenTechDto updateBrand(@RequestBody ScreenTechDto dto) throws ElementNotFoundException {
        return service.updateScreenTech(dto);
    }

    @GetMapping("/find-by-id")
    public ScreenTechDto findBrandById(@RequestParam Long id) throws ElementNotFoundException {
        return service.getScreenTechById(id);
    }

    @CrossOrigin(origins = "http://locathost:3000")
    @GetMapping("/get-all")
    public List<ScreenTechDto> getAllBrand() {
        return service.getAllScreenTech();
    }

    @PostMapping("/get-by-name")
    public ScreenTechDto getBrandByName(@RequestParam String name) throws ElementNotFoundException {
        return service.getScreenTechByName(name);
    }
}
