package com.parse.steam.controllers;

import com.parse.steam.services.ItemFetcherService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/ts")
public class ParseController {
    private final ItemFetcherService fetcher;

    @GetMapping("/fetch")
    public void fetch() {
        fetcher.fetchItemsToKafka();
    }
}
