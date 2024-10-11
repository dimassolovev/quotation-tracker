package com.dimas.moexdataservice.service;

import com.dimas.moexdataservice.mapper.custom.CurrencyDataMapper;
import com.dimas.moexdataservice.model.kafka.DataFromAggregator;
import com.dimas.moexdataservice.model.kafka.currency.MoexCurrencyData;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaMessageListener {

    @KafkaListener(topics = "${kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void onMessage(ConsumerRecord<Long, DataFromAggregator<List<MoexCurrencyData>>> data) {
        log.info("Received data from topic {}", data.topic());
    }
}
