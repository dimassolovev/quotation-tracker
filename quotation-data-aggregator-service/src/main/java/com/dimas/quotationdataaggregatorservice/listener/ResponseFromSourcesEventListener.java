package com.dimas.quotationdataaggregatorservice.listener;

import com.dimas.quotationdataaggregatorservice.constant.property.KafkaConfigurationProperty;
import com.dimas.quotationdataaggregatorservice.event.ResponseFromSourcesEvent;

import com.dimas.quotationdataaggregatorservice.kafka.producer.KafkaProducerImplementation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * MoexResponseEvent listener
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class ResponseFromSourcesEventListener {
    private final KafkaProducerImplementation kafkaProducer;
    private final KafkaConfigurationProperty kafkaConfigurationProperty;

    /**
     * The method listens for event and immediately sends data to kafka.
     * @param event an event that is triggered after receiving data from moex.
     */
    @EventListener
    public void handleMoexResponse(ResponseFromSourcesEvent event) {
        try {
            this.kafkaProducer.sendMessage(
                    event.getDataFromExternalServices(),
                    kafkaConfigurationProperty.getTopic()
            );
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
