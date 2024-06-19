package com.parse.steam.controllers;

import com.parse.steam.dtos.WashingMachineDto;
import com.parse.steam.dtos.stat.PredicateDto;
import com.parse.steam.exceptions.ElementNotFoundException;
import com.parse.steam.services.WashingMachineService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://locathost:4200")
@AllArgsConstructor
@RestController
@RequestMapping("/washing-machine")
public class WashingMachineController {
    private final WashingMachineService service;

    @PostMapping("/save")
    public WashingMachineDto saveWashingMachine(@RequestBody WashingMachineDto dto) {
        return service.saveWashingMachine(dto);
    }

    @PostMapping("/save-all")
    public List<WashingMachineDto> saveAllWashingMachine(@RequestBody List<WashingMachineDto> dtoList) {
        return service.saveWashingMachineList(dtoList);
    }

    @PostMapping("/archive")
    public WashingMachineDto archiveWashingMachine(@RequestParam Long id) throws ElementNotFoundException {
        return service.archiveWashingMachine(id);
    }

    @PostMapping("/unarchive")
    public WashingMachineDto unarchiveWashingMachine(@RequestParam Long id) throws ElementNotFoundException {
        return service.unarchiveWashingMachine(id);
    }

    @PostMapping("/update")
    public WashingMachineDto updateWashingMachine(@RequestBody WashingMachineDto dto) throws ElementNotFoundException {
        return service.updateWashingMachine(dto);
    }

    @GetMapping("/find-by-id")
    public WashingMachineDto findWashingMachineById(@RequestParam Long id) throws ElementNotFoundException {
        return service.getWashingMachineById(id);
    }

    @GetMapping("/get-all")
    public List<WashingMachineDto> getAllWashingMachine() {
        return service.getAllWashingMachine();
    }

    @PostMapping("/predicate")
    public List<PredicateDto> predicatePrice(@RequestParam Long itemId) {
        return service.predicateUpPrice(itemId);
    }
}
