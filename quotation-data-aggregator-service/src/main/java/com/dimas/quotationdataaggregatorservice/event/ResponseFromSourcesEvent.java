package com.dimas.quotationdataaggregatorservice.event;

import com.dimas.quotationdataaggregatorservice.model.external.DataFromExternalServices;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ResponseFromSourcesEvent extends ApplicationEvent {
    private final DataFromExternalServices<?> dataFromExternalServices;
    private final String topicName;

    public ResponseFromSourcesEvent(Object source, DataFromExternalServices<?> dataFromExternalServices, String topicName) {
        super(source);
        this.dataFromExternalServices = dataFromExternalServices;
        this.topicName = topicName;
    }
}
