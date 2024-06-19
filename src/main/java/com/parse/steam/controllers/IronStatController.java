package com.parse.steam.controllers;

import com.parse.steam.dtos.stat.PriceTimeDto;
import com.parse.steam.dtos.stat.StatCountMonitorMarketDto;
import com.parse.steam.dtos.stat.StatLowestPriceDto;
import com.parse.steam.services.IronStatService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://locathost:4200")
@AllArgsConstructor
@RestController
@RequestMapping("/iron-stat")
public class IronStatController {
    private final IronStatService service;

    @PostMapping("/get-stat-by-item-id")
    public PriceTimeDto findStatByIronId(@RequestParam Long itemId, @RequestParam Long marketId) {
        return service.findStatByIronId(itemId, marketId);
    }

    @PostMapping("/get-all-stat-by-item-id")
    public List<PriceTimeDto> findAllStatByIronId(@RequestParam Long itemId) {
        return service.findAllStatByIronId(itemId);
    }

    @PostMapping("/count-iron-market")
    public List<StatCountMonitorMarketDto> countIronMarket() {
        return service.countIronMarket();
    }

    @PostMapping("/count-iron-in-redis")
    public List<StatCountMonitorMarketDto> countIronMarketInRedis() {
        return service.countIronMarketInRedis();
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
