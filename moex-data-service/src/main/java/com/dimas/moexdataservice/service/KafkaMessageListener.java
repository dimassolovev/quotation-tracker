package com.dimas.moexdataservice.service;

import com.dimas.moexdataservice.model.kafka.DataFromAggregator;

import lombok.extern.slf4j.Slf4j;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaMessageListener {

    @KafkaListener(topics = "${kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void onMessage(ConsumerRecord<Long, DataFromAggregator<?>> data) {
        log.info("Received data from topic {}: {} ", data.topic(), data.value());
    }
}
