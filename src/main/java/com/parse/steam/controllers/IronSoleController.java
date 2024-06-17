package com.parse.steam.controllers;

import com.parse.steam.dtos.IronSoleDto;
import com.parse.steam.exceptions.ElementNotFoundException;
import com.parse.steam.services.IronSoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://locathost:3000")
@AllArgsConstructor
@RestController
@RequestMapping("/iron-sole")
public class IronSoleController {
    private final IronSoleService service;

    @PostMapping("/save")
    public IronSoleDto saveBrand(@RequestBody IronSoleDto dto) {
        return service.saveIronSole(dto);
    }

    @PostMapping("/save-all")
    public List<IronSoleDto> saveAllBrand(@RequestBody List<IronSoleDto> dtoList) {
        return service.saveIronSoleList(dtoList);
    }

    @PostMapping("/archive")
    public IronSoleDto archiveBrand(@RequestParam Long id) throws ElementNotFoundException {
        return service.archiveIronSole(id);
    }

    @PostMapping("/unarchive")
    public IronSoleDto unarchiveBrand(@RequestParam Long id) throws ElementNotFoundException {
        return service.unarchiveIronSole(id);
    }

    @PostMapping("/update")
    public IronSoleDto updateBrand(@RequestBody IronSoleDto dto) throws ElementNotFoundException {
        return service.updateIronSole(dto);
    }

    @GetMapping("/find-by-id")
    public IronSoleDto findBrandById(@RequestParam Long id) throws ElementNotFoundException {
        return service.getIronSoleById(id);
    }

    @CrossOrigin(origins = "http://locathost:3000")
    @GetMapping("/get-all")
    public List<IronSoleDto> getAllBrand() {
        return service.getAllIronSole();
    }

    @PostMapping("/get-by-name")
    public IronSoleDto getBrandByName(@RequestParam String name) throws ElementNotFoundException {
        return service.getIronSoleByName(name);
    }
}
