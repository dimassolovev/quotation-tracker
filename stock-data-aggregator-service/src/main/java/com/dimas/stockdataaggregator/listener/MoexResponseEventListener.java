package com.dimas.stockdataaggregator.listener;

import com.dimas.stockdataaggregator.event.MoexResponseEvent;
import com.dimas.stockdataaggregator.kafka.producer.KafkaCurrencyDataMessagePublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MoexResponseEventListener {
    private final KafkaCurrencyDataMessagePublisher kafkaCurrencyDataMessagePublisher;

    @EventListener
    public void handleMoexResponse(MoexResponseEvent event) {
        try {
            kafkaCurrencyDataMessagePublisher.sendMessage(event.getDataFromExternalServices());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
