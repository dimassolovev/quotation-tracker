package com.dimas.quotationdataaggregatorservice.kafka.producer;

import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

public interface KafkaProducer<K, V> {
    CompletableFuture<SendResult<K, V>> sendMessage(
            V data, String topic
    );
}