package com.parse.steam.controllers;

import com.parse.steam.dtos.TVMarketDto;
import com.parse.steam.exceptions.ElementIsArchivedException;
import com.parse.steam.exceptions.ElementNotFoundException;
import com.parse.steam.services.TVMarketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://locathost:4200")
@AllArgsConstructor
@RestController
@RequestMapping("/tv-market")
public class TVMarketController {
    private final TVMarketService service;

    @PostMapping("/save")
    public TVMarketDto saveTVMarket(@RequestBody TVMarketDto dto) {
        return service.saveTVMarket(dto);
    }

    @PostMapping("/save-all")
    public List<TVMarketDto> saveAllTVMarket(@RequestBody List<TVMarketDto> dtoList) {
        return service.saveTVMarketList(dtoList);
    }

    @PostMapping("/archive")
    public TVMarketDto archiveTVMarket(@RequestParam Long id) throws ElementNotFoundException {
        return service.archiveTVMarket(id);
    }

    @PostMapping("/unarchive")
    public TVMarketDto unarchiveTVMarket(@RequestParam Long id) throws ElementNotFoundException {
        return service.unarchiveTVMarket(id);
    }

    @PostMapping("/update")
    public TVMarketDto updateTVMarket(@RequestBody TVMarketDto dto) throws ElementNotFoundException {
        return service.updateTVMarket(dto);
    }

    @GetMapping("/find-by-id")
    public TVMarketDto findTVMarketById(@RequestParam Long id) throws ElementNotFoundException {
        return service.getTVMarketById(id);
    }

    @GetMapping("/get-all")
    public List<TVMarketDto> getAllTVMarket() {
        return service.getAllTVMarket();
    }

    @GetMapping("/insert-to-redis")
    public TVMarketDto insertToRedis(@RequestParam Long id) throws ElementNotFoundException, ElementIsArchivedException {
        return service.insertToRedis(id);
    }

    @GetMapping("/delete-from-redis")
    public TVMarketDto deleteFromRedis(@RequestParam Long id) throws ElementNotFoundException, ElementIsArchivedException {
        return service.deleteFromRedis(id);
    }

    @GetMapping("/clean-redis")
    public boolean cleanRedis() {
        return service.cleanRedis();
    }
}
