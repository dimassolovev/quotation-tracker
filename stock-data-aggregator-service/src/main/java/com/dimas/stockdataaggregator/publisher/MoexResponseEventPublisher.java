package com.dimas.stockdataaggregator.publisher;

import com.dimas.stockdataaggregator.event.MoexResponseEvent;
import com.dimas.stockdataaggregator.model.external.DataFromExternalServices;

import lombok.RequiredArgsConstructor;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MoexResponseEventPublisher {
    private final ApplicationEventPublisher eventPublisher;

    public void publishMoexResponseEvent(DataFromExternalServices dataFromExternalServices) {
        this.eventPublisher.publishEvent(new MoexResponseEvent(this, dataFromExternalServices));
    }
}
