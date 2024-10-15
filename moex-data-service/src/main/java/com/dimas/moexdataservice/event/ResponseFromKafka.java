package com.dimas.moexdataservice.event;

import com.dimas.moexdataservice.model.kafka.currency.CurrencyData;
import com.dimas.moexdataservice.model.kafka.currency.DataFromAggregator;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.List;

@Getter
public class ResponseFromKafka extends ApplicationEvent  {
    private final DataFromAggregator<List<CurrencyData>> dataFromAggregator;

    public ResponseFromKafka(Object source, DataFromAggregator<List<CurrencyData>> dataFromAggregator) {
        super(source);
        this.dataFromAggregator = dataFromAggregator;
    }
}
