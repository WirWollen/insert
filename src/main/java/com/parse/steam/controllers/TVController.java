package com.parse.steam.controllers;

import com.parse.steam.dtos.TVDto;
import com.parse.steam.exceptions.ElementNotFoundException;
import com.parse.steam.services.TVService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://locathost:4200")
@AllArgsConstructor
@RestController
@RequestMapping("/tv")
public class TVController {
    private final TVService service;

    @PostMapping("/save")
    public TVDto saveTV(@RequestBody TVDto dto) {
        return service.saveTV(dto);
    }

    @PostMapping("/save-all")
    public List<TVDto> saveAllTV(@RequestBody List<TVDto> dtoList) {
        return service.saveTVList(dtoList);
    }

    @PostMapping("/archive")
    public TVDto archiveTV(@RequestParam Long id) throws ElementNotFoundException {
        return service.archiveTV(id);
    }

    @PostMapping("/unarchive")
    public TVDto unarchiveTV(@RequestParam Long id) throws ElementNotFoundException {
        return service.unarchiveTV(id);
    }

    @PostMapping("/update")
    public TVDto updateTV(@RequestBody TVDto dto) throws ElementNotFoundException {
        return service.updateTV(dto);
    }

    @GetMapping("/find-by-id")
    public TVDto findTVById(@RequestParam Long id) throws ElementNotFoundException {
        return service.getTVById(id);
    }

    @GetMapping("/get-all")
    public List<TVDto> getAllTV() {
        return service.getAllTV();
    }
}
