package com.parse.steam.controllers;

import com.parse.steam.dtos.BrandDto;
import com.parse.steam.exceptions.ElementNotFoundException;
import com.parse.steam.services.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://locathost:4200")
@AllArgsConstructor
@RestController
@RequestMapping("/brand")
public class BrandController {
    private final BrandService service;

    @PostMapping("/save")
    public BrandDto saveBrand(@RequestBody BrandDto dto) {
        return service.saveBrand(dto);
    }

    @PostMapping("/save-all")
    public List<BrandDto> saveAllBrand(@RequestBody List<BrandDto> dtoList) {
        return service.saveBrandList(dtoList);
    }

    @PostMapping("/archive")
    public BrandDto archiveBrand(@RequestParam Long id) throws ElementNotFoundException {
        return service.archiveBrand(id);
    }

    @PostMapping("/unarchive")
    public BrandDto unarchiveBrand(@RequestParam Long id) throws ElementNotFoundException {
        return service.unarchiveBrand(id);
    }

    @PostMapping("/update")
    public BrandDto updateBrand(@RequestBody BrandDto dto) throws ElementNotFoundException {
        return service.updateBrand(dto);
    }

    @GetMapping("/find-by-id")
    public BrandDto findBrandById(@RequestParam Long id) throws ElementNotFoundException {
        return service.getBrandById(id);
    }

    @GetMapping("/get-all")
    public List<BrandDto> getAllBrand() {
        return service.getAllBrand();
    }

    @PostMapping("/get-by-name")
    public BrandDto getBrandByName(@RequestParam String name) throws ElementNotFoundException {
        return service.getBrandByName(name);
    }
}
