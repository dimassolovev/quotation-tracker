package com.dimas.stockdataaggregator.publisher;

import com.dimas.stockdataaggregator.event.ResponseFromSourcesEvent;
import com.dimas.stockdataaggregator.model.external.DataFromExternalServices;

import lombok.RequiredArgsConstructor;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;


/**
 * MoexResponseEvent Publisher Class
 */
@Component
@RequiredArgsConstructor
public class MoexResponseEventPublisher {
    private final ApplicationEventPublisher eventPublisher;

    /**
     * The method publishes ResponseFromSourcesEvent.
     * @param dataFromExternalServices data container
     */
    public void publishResponseFromSourcesEvent(DataFromExternalServices<?> dataFromExternalServices) {
        this.eventPublisher.publishEvent(new ResponseFromSourcesEvent(this, dataFromExternalServices));
    }
}
