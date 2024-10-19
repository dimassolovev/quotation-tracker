package com.dimas.quotationdataaggregatorservice.publisher;

import com.dimas.quotationdataaggregatorservice.event.CurrencyResponseFromMoexEvent;
import com.dimas.quotationdataaggregatorservice.model.external.moex.currency.CurrencyData;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MoexCurrencyResponseEventPublisher implements Publisher<List<CurrencyData>> {
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void publishResponseFromSourcesEvent(List<CurrencyData> dataFromExternalServices, String topicName) {
        this.eventPublisher.publishEvent(
                new CurrencyResponseFromMoexEvent(this, dataFromExternalServices, topicName)
        );
    }
}
