package com.dimas.quotationdataaggregatorservice.publisher;

import com.dimas.quotationdataaggregatorservice.event.ResponseFromSourcesEvent;
import com.dimas.quotationdataaggregatorservice.model.external.DataFromExternalServices;
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
    public void publishResponseFromSourcesEvent(DataFromExternalServices<List<CurrencyData>> dataFromExternalServices, String topicName) {
        this.eventPublisher.publishEvent(
                new ResponseFromSourcesEvent(this, dataFromExternalServices, topicName)
        );
    }
}
