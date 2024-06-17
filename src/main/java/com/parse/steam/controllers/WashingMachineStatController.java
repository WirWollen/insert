package com.parse.steam.controllers;

import com.parse.steam.dtos.stat.PriceTimeDto;
import com.parse.steam.dtos.stat.StatCountMonitorMarketDto;
import com.parse.steam.dtos.stat.StatLowestPriceDto;
import com.parse.steam.services.WashingMachineStatService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://locathost:4200")
@AllArgsConstructor
@RestController
@RequestMapping("/washing-machine-stat")
public class WashingMachineStatController {
    private final WashingMachineStatService service;

    @PostMapping("/get-stat-by-item-id")
    public PriceTimeDto findStatByWashingMachineId(@RequestParam Long itemId, @RequestParam Long marketId) {
        return service.findStatByWashingMachineId(itemId, marketId);
    }

    @PostMapping("/get-all-stat-by-item-id")
    public List<PriceTimeDto> findAllStatByWashingMachineId(@RequestParam Long itemId) {
        return service.findAllStatByWashingMachineId(itemId);
    }

    @PostMapping("/count-washing-machine-market")
    public List<StatCountMonitorMarketDto> countWashingMachineMarket() {
        return service.countWashingMachineMarket();
    }

    @PostMapping("/count-washing-machine-in-redis")
    public List<StatCountMonitorMarketDto> countMonitorMarketInRedis() {
        return service.countWashingMachineMarketInRedis();
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
