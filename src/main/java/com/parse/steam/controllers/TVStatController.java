package com.parse.steam.controllers;

import com.parse.steam.dtos.stat.PriceTimeDto;
import com.parse.steam.dtos.stat.StatCountMonitorMarketDto;
import com.parse.steam.dtos.stat.StatLowestPriceDto;
import com.parse.steam.services.MonitorStatService;
import com.parse.steam.services.TVStatService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://locathost:4200")
@AllArgsConstructor
@RestController
@RequestMapping("/tv-stat")
public class TVStatController {
    private final TVStatService service;

    @PostMapping("/get-stat-by-item-id")
    public PriceTimeDto findStatByMonitorId(@RequestParam Long itemId, @RequestParam Long marketId) {
        return service.findStatByTVId(itemId, marketId);
    }

    @PostMapping("/get-all-stat-by-item-id")
    public List<PriceTimeDto> findAllStatByMonitorId(@RequestParam Long itemId) {
        return service.findAllStatByTVId(itemId);
    }

    @PostMapping("/count-tv-market")
    public List<StatCountMonitorMarketDto> countMonitorMarket() {
        return service.countTVMarket();
    }

    @PostMapping("/count-tv-in-redis")
    public List<StatCountMonitorMarketDto> countMonitorMarketInRedis() {
        return service.countTVMarket();
    }

    @PostMapping("/find-current-prices")
    public List<StatLowestPriceDto> findCurrentPrices(@RequestParam Long itemId) {
        return service.findCurrentPrices(itemId);
    }

    @PostMapping("/find-lowest-prices")
    public List<StatLowestPriceDto> findLowestPrices(@RequestParam Long itemId) {
        return service.findLowestPrices(itemId);
    }
}
