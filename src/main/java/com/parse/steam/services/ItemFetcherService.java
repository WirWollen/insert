package com.parse.steam.services;

import com.parse.steam.converters.central.ItemConverter;
import com.parse.steam.dtos.central.ItemDto;
import com.parse.steam.repo.parsed.ItemRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemFetcherService {
    private final ItemRepo itemRepo;
    @Value(value = "${data.page-size}")
    private int pageSize;
    private final ParsedItemSenderService sender;

    public boolean fetchItemsToKafka() {
        int currPage = 0;

        while (true) {
            Set<ItemDto> items = getItemsByPage(currPage);
            if (items.isEmpty()) {
                break;
            }
            items.stream().forEach(sender::send);
            currPage++;
        }
        return true;
    }

    private Set<ItemDto> getItemsByPage(int currPage) {
        return itemRepo.findAll(PageRequest.of(currPage * pageSize / pageSize, pageSize)).getContent().stream().map(ItemConverter::toDto).collect(Collectors.toSet());
    }
}
