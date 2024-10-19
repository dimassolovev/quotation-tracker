package com.dimas.moexdataservice.event;

import com.dimas.moexdataservice.model.kafka.currency.CurrencyData;
import com.dimas.moexdataservice.model.kafka.currency.DataFromAggregator;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ResponseFromKafka extends ApplicationEvent  {
    private final DataFromAggregator<CurrencyData> dataFromAggregator;

    public ResponseFromKafka(Object source, DataFromAggregator<CurrencyData> dataFromAggregator) {
        super(source);
        this.dataFromAggregator = dataFromAggregator;
    }
}
