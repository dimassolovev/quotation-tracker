package com.dimas.stockdataaggregator.publisher;

import com.dimas.stockdataaggregator.event.MoexResponseEvent;
import com.dimas.stockdataaggregator.model.external.DataFromExternalServices;

import com.dimas.stockdataaggregator.model.external.moex.currency.CurrencyData;
import lombok.RequiredArgsConstructor;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MoexResponseEventPublisher {
    private final ApplicationEventPublisher eventPublisher;

    public void publishMoexResponseEvent(DataFromExternalServices<List<CurrencyData>> dataFromExternalServices) {
        this.eventPublisher.publishEvent(new MoexResponseEvent(this, dataFromExternalServices));
    }
}
