package com.dimas.quotationdataaggregatorservice.event;

import com.dimas.quotationdataaggregatorservice.model.external.DataFromExternalServices;

import lombok.Getter;

import org.springframework.context.ApplicationEvent;

@Getter
public class ResponseFromSourcesEvent extends ApplicationEvent {
    private final DataFromExternalServices<?> dataFromExternalServices;

    public ResponseFromSourcesEvent(Object source, DataFromExternalServices<?> dataFromExternalServices) {
        super(source);
        this.dataFromExternalServices = dataFromExternalServices;
    }
}
