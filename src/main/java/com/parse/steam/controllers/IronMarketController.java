package com.parse.steam.controllers;

import com.parse.steam.dtos.IronMarketDto;
import com.parse.steam.dtos.TVMarketDto;
import com.parse.steam.exceptions.ElementIsArchivedException;
import com.parse.steam.exceptions.ElementNotFoundException;
import com.parse.steam.services.IronMarketService;
import com.parse.steam.services.TVMarketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://locathost:4200")
@AllArgsConstructor
@RestController
@RequestMapping("/iron-market")
public class IronMarketController {
    private final IronMarketService service;

    @PostMapping("/save")
    public IronMarketDto saveIronMarket(@RequestBody IronMarketDto dto) {
        return service.saveIronMarket(dto);
    }

    @PostMapping("/save-all")
    public List<IronMarketDto> saveAllIronMarket(@RequestBody List<IronMarketDto> dtoList) {
        return service.saveIronMarketList(dtoList);
    }

    @PostMapping("/archive")
    public IronMarketDto archiveIronMarket(@RequestParam Long id) throws ElementNotFoundException {
        return service.archiveIronMarket(id);
    }

    @PostMapping("/unarchive")
    public IronMarketDto unarchiveIronMarket(@RequestParam Long id) throws ElementNotFoundException {
        return service.unarchiveIronMarket(id);
    }

    @PostMapping("/update")
    public IronMarketDto updateIronMarket(@RequestBody IronMarketDto dto) throws ElementNotFoundException {
        return service.updateIronMarket(dto);
    }

    @GetMapping("/find-by-id")
    public IronMarketDto findIronMarketById(@RequestParam Long id) throws ElementNotFoundException {
        return service.getIronMarketById(id);
    }

    @GetMapping("/get-all")
    public List<IronMarketDto> getAllIronMarket() {
        return service.getAllIronMarket();
    }

    @GetMapping("/insert-to-redis")
    public IronMarketDto insertToRedis(@RequestParam Long id) throws ElementNotFoundException, ElementIsArchivedException {
        return service.insertToRedis(id);
    }

    @GetMapping("/delete-from-redis")
    public IronMarketDto deleteFromRedis(@RequestParam Long id) throws ElementNotFoundException, ElementIsArchivedException {
        return service.deleteFromRedis(id);
    }

    @GetMapping("/clean-redis")
    public boolean cleanRedis() {
        return service.cleanRedis();
    }
}
