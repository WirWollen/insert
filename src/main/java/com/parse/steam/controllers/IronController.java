package com.parse.steam.controllers;

import com.parse.steam.dtos.IronDto;
import com.parse.steam.dtos.TVDto;
import com.parse.steam.exceptions.ElementNotFoundException;
import com.parse.steam.services.IronService;
import com.parse.steam.services.TVService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://locathost:4200")
@AllArgsConstructor
@RestController
@RequestMapping("/iron")
public class IronController {
    private final IronService service;

    @PostMapping("/save")
    public IronDto saveTV(@RequestBody IronDto dto) {
        return service.saveIron(dto);
    }

    @PostMapping("/save-all")
    public List<IronDto> saveAllTV(@RequestBody List<IronDto> dtoList) {
        return service.saveIronList(dtoList);
    }

    @PostMapping("/archive")
    public IronDto archiveTV(@RequestParam Long id) throws ElementNotFoundException {
        return service.archiveIron(id);
    }

    @PostMapping("/unarchive")
    public IronDto unarchiveTV(@RequestParam Long id) throws ElementNotFoundException {
        return service.unarchiveIron(id);
    }

    @PostMapping("/update")
    public IronDto updateTV(@RequestBody IronDto dto) throws ElementNotFoundException {
        return service.updateIron(dto);
    }

    @GetMapping("/find-by-id")
    public IronDto findTVById(@RequestParam Long id) throws ElementNotFoundException {
        return service.getIronById(id);
    }

    @GetMapping("/get-all")
    public List<IronDto> getAllTV() {
        return service.getAllIron();
    }
}
