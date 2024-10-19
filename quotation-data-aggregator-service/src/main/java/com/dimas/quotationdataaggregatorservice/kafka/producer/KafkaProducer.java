package com.dimas.quotationdataaggregatorservice.kafka.producer;

public interface KafkaProducer<T> {
    void sendMessage(
            T data, String topic
    );
}
