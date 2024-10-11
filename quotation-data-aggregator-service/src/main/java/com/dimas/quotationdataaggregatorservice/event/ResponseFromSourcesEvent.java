package com.dimas.quotationdataaggregatorservice.event;

import com.dimas.quotationdataaggregatorservice.model.external.DataFromExternalServices;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * An event that is posted after receiving data from moex.
 */
@Getter
public class ResponseFromSourcesEvent extends ApplicationEvent {
    private final DataFromExternalServices<?> dataFromExternalServices;

    public ResponseFromSourcesEvent(Object source, DataFromExternalServices<?> dataFromExternalServices) {
        super(source);
        this.dataFromExternalServices = dataFromExternalServices;
    }
}
