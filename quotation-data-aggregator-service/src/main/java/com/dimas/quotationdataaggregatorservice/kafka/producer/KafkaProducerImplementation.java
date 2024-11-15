package com.dimas.quotationdataaggregatorservice.kafka.producer;

import com.dimas.quotationdataaggregatorservice.model.external.DataFromExternalServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducerImplementation implements KafkaProducer<String, DataFromExternalServices<?>> {
    private final KafkaTemplate<String, DataFromExternalServices<?>> kafkaTemplate;
    private final DateTimeFormatter dateTimeFormatter;

    public CompletableFuture<SendResult<String, DataFromExternalServices<?>>> sendMessage(DataFromExternalServices<?> dataFromExternalServices, String topicName) {
        return this.kafkaTemplate
                .send(
                        topicName,
                        LocalDateTime.now().format(dateTimeFormatter),
                        dataFromExternalServices
                );
    }
}