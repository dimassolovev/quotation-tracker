package com.dimas.stockdataaggregator.listener;

import com.dimas.stockdataaggregator.event.ResponseFromSourcesEvent;
import com.dimas.stockdataaggregator.kafka.producer.KafkaDataMessagePublisher;

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
    private final KafkaDataMessagePublisher kafkaCurrencyDataMessagePublisher;

    /**
     * The method listens for event and immediately sends data to kafka.
     * @param event an event that is triggered after receiving data from moex.
     */
    @EventListener
    public void handleMoexResponse(ResponseFromSourcesEvent event) {
        try {
            this.kafkaCurrencyDataMessagePublisher.sendMessage(
                    event.getDataFromExternalServices()
            );
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
