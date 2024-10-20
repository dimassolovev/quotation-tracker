package com.dimas.moexdataservice.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface KafkaMessageListener {
    void onMessage(ConsumerRecord<String, String> consumerRecord);
}
