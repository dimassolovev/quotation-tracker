package com.dimas.moexdataservice.publisher;

import com.dimas.moexdataservice.event.ResponseFromKafka;
import com.dimas.moexdataservice.model.kafka.currency.CurrencyData;
import com.dimas.moexdataservice.model.kafka.currency.DataFromAggregator;

import lombok.RequiredArgsConstructor;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class KafkaResponsePublisher implements Publisher<List<CurrencyData>> {
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publishResponseFromSourcesEvent(DataFromAggregator<List<CurrencyData>> dataFromExternalServices) {
        this.applicationEventPublisher.publishEvent(
            new ResponseFromKafka(this, dataFromExternalServices)
        );
    }
}
