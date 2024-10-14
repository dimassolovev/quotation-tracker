package com.dimas.quotationdataaggregatorservice.listener;

import com.dimas.quotationdataaggregatorservice.event.ResponseFromSourcesEvent;
import com.dimas.quotationdataaggregatorservice.kafka.producer.KafkaProducerImplementation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ResponseFromSourcesEventListener {
    private final KafkaProducerImplementation kafkaProducer;

    @EventListener
    public void handleMoexResponse(ResponseFromSourcesEvent event) {
        try {
            this.kafkaProducer.sendMessage(
                    event.getDataFromExternalServices(),
                    event.getTopicName()
            );
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
