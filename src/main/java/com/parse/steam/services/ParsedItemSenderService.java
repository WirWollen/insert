package com.parse.steam.services;

import com.parse.steam.dtos.central.ItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParsedItemSenderService {

    private final KafkaTemplate<String, ItemDto> template;

    @Value(value = "${spring.kafka.producer.topic}")
    private String topic;

    public void send(ItemDto item) {
        template.send(topic, item);
    }
}
