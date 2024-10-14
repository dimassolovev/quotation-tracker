package com.dimas.moexnotificationservice.service;

import com.dimas.moexnotificationservice.model.currency.CurrencyData;
import com.dimas.moexnotificationservice.model.currency.DataFromAggregator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaMessageListener {
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void onMessage(ConsumerRecord<Long, String> consumerRecord) {
        log.info("Received data from topic {}, timestamp: {}", consumerRecord.topic(), consumerRecord.key());

        try {
            DataFromAggregator<List<CurrencyData>> dataFromAggregator = objectMapper.readValue(consumerRecord.value(), new TypeReference<>() {});
        }


        catch (Exception exception) {
            log.error(exception.getMessage(), exception);
        }
    }
}
