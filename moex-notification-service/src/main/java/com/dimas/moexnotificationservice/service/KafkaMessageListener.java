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
        log.info("Received data from topic {}: {} ", consumerRecord.topic(), consumerRecord.value());
        try {
            DataFromAggregator dataFromAggregator = objectMapper.readValue(consumerRecord.value(), DataFromAggregator.class);
            System.out.println(dataFromAggregator.getData());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
