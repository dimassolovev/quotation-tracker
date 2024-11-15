package com.dimas.quotationdataaggregatorservice.listener;

import com.dimas.quotationdataaggregatorservice.event.CurrencyResponseFromMoexEvent;
import com.dimas.quotationdataaggregatorservice.service.transactional.OutboxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ResponseFromSourcesEventListener {
    private final OutboxService outboxService;

    @EventListener
    public void handleMoexResponse(CurrencyResponseFromMoexEvent event) {
        this.outboxService.saveAll(event.getTopicName(), event.getCurrenciesList());
    }
}
