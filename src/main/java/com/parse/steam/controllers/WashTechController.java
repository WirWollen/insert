package com.parse.steam.controllers;

import com.parse.steam.dtos.WashTechDto;
import com.parse.steam.exceptions.ElementNotFoundException;
import com.parse.steam.services.WashTechService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://locathost:3000")
@AllArgsConstructor
@RestController
@RequestMapping("/wash-tech")
public class WashTechController {
    private final WashTechService service;

    @PostMapping("/save")
    public WashTechDto saveBrand(@RequestBody WashTechDto dto) {
        return service.saveWashTech(dto);
    }

    @PostMapping("/save-all")
    public List<WashTechDto> saveAllBrand(@RequestBody List<WashTechDto> dtoList) {
        return service.saveWashTechList(dtoList);
    }

    @PostMapping("/archive")
    public WashTechDto archiveBrand(@RequestParam Long id) throws ElementNotFoundException {
        return service.archiveWashTech(id);
    }

    @PostMapping("/unarchive")
    public WashTechDto unarchiveBrand(@RequestParam Long id) throws ElementNotFoundException {
        return service.unarchiveWashTech(id);
    }

    @PostMapping("/update")
    public WashTechDto updateBrand(@RequestBody WashTechDto dto) throws ElementNotFoundException {
        return service.updateWashTech(dto);
    }

    @GetMapping("/find-by-id")
    public WashTechDto findBrandById(@RequestParam Long id) throws ElementNotFoundException {
        return service.getWashTechById(id);
    }

    @CrossOrigin(origins = "http://locathost:3000")
    @GetMapping("/get-all")
    public List<WashTechDto> getAllBrand() {
        return service.getAllWashTech();
    }

    @PostMapping("/get-by-name")
    public WashTechDto getBrandByName(@RequestParam String name) throws ElementNotFoundException {
        return service.getWashTechByName(name);
    }
}
