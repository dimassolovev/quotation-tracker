package com.dimas.quotationdataaggregatorservice.listener;

import com.dimas.quotationdataaggregatorservice.event.CurrencyResponseFromMoexEvent;
import com.dimas.quotationdataaggregatorservice.kafka.producer.KafkaProducerImplementation;
import com.dimas.quotationdataaggregatorservice.model.external.DataFromExternalServices;
import com.dimas.quotationdataaggregatorservice.model.external.moex.currency.CurrencyData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ResponseFromSourcesEventListener {
    private final KafkaProducerImplementation kafkaProducer;

    @EventListener
    public void handleMoexResponse(CurrencyResponseFromMoexEvent event) {
        List<CurrencyData> currenciesList = event.getCurrenciesList();
        String topicName = event.getTopicName();

        for (CurrencyData currencyData : currenciesList) {
            this.kafkaProducer.sendMessage(
                    new DataFromExternalServices<>(currencyData),
                    topicName
            );
        }
    }
}
