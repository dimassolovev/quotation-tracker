package com.dimas.quotationdataaggregatorservice.kafka.producer;

import com.dimas.quotationdataaggregatorservice.model.external.DataFromExternalServices;

public interface KafkaProducer <T> {
    void sendMessage(
            T data, String topic
    );
}
