package com.dimas.moexdataservice.event;

import com.dimas.moexdataservice.model.kafka.currency.DataFromAggregator;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ResponseFromKafka extends ApplicationEvent  {
    private final DataFromAggregator<?> dataFromAggregator;

    public ResponseFromKafka(Object source, DataFromAggregator<?> dataFromAggregator) {
        super(source);
        this.dataFromAggregator = dataFromAggregator;
    }
}
