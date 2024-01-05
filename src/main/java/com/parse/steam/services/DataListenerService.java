package com.parse.steam.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.parse.steam.dtos.central.ItemDto;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DataListenerService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final UpdateDBService updateDBService;

    @KafkaListener(topics = "${spring.kafka.consumer.topic-list}", groupId = "${spring.kafka.consumer.group-id}")
    public void listenerData(String message) throws JsonProcessingException {
        updateDBService.insert(objectMapper.readValue(message, ItemDto.class));
    }
}
