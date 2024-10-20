package com.dimas.moexdataservice.service;

import com.dimas.moexdataservice.model.kafka.currency.CurrencyData;
import com.dimas.moexdataservice.model.kafka.currency.DataFromAggregator;
import com.dimas.moexdataservice.publisher.KafkaResponsePublisher;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaMessageListenerImplementation implements KafkaMessageListener {
    private final ObjectMapper objectMapper;
    private final KafkaResponsePublisher kafkaResponsePublisher;

    @Override
    @KafkaListener(topics = "${kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void onMessage(ConsumerRecord<String, String> consumerRecord) {
        try {
            DataFromAggregator<CurrencyData> dataFromAggregator = objectMapper.readValue(consumerRecord.value(), new TypeReference<>() {
            });
            System.out.println(dataFromAggregator);

            this.kafkaResponsePublisher.publishResponseFromSourcesEvent(dataFromAggregator);
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
        }
    }
}
