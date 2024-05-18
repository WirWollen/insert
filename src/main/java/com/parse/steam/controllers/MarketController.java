package com.parse.steam.controllers;

import com.parse.steam.dtos.MarketDto;
import com.parse.steam.exceptions.ElementNotFoundException;
import com.parse.steam.services.MarketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://locathost:4200")
@AllArgsConstructor
@RestController
@RequestMapping("/market")
public class MarketController {
    private final MarketService service;

    @PostMapping("/save")
    public MarketDto saveMarket(@RequestBody MarketDto dto) {
        return service.saveMarket(dto);
    }

    @PostMapping("/save-all")
    public List<MarketDto> saveAllMarket(@RequestBody List<MarketDto> dtoList) {
        return service.saveMarketList(dtoList);
    }

    @PostMapping("/archive")
    public MarketDto archiveMarket(@RequestParam Long id) throws ElementNotFoundException {
        return service.archiveMarket(id);
    }

    @PostMapping("/unarchive")
    public MarketDto unarchiveMarket(@RequestParam Long id) throws ElementNotFoundException {
        return service.unarchiveMarket(id);
    }

    @PostMapping("/update")
    public MarketDto updateMarket(@RequestBody MarketDto dto) throws ElementNotFoundException {
        return service.updateMarket(dto);
    }

    @GetMapping("/find-by-id")
    public MarketDto findMarketById(@RequestParam Long id) throws ElementNotFoundException {
        return service.getMarketById(id);
    }

    @GetMapping("/get-all")
    public List<MarketDto> getAllMarket() {
        return service.getAllMarket();
    }

    @PostMapping("/get-by-name")
    public MarketDto getMarketByName(@RequestParam String name) throws ElementNotFoundException {
        return service.getMarketByName(name);
    }
}
