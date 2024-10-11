package com.dimas.quotationdataaggregatorservice.publisher;

import com.dimas.quotationdataaggregatorservice.event.ResponseFromSourcesEvent;
import com.dimas.quotationdataaggregatorservice.model.external.DataFromExternalServices;

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
