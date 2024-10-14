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
public class MoexCurrencyResponseEventPublisher {
    private final ApplicationEventPublisher eventPublisher;

    public void publishResponseFromSourcesEvent(DataFromExternalServices<List<CurrencyData>> dataFromExternalServices) {
        this.eventPublisher.publishEvent(new ResponseFromSourcesEvent(this, dataFromExternalServices));
    }
}
