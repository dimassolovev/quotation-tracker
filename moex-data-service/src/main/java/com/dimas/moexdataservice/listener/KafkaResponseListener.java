package com.dimas.moexdataservice.listener;

import com.dimas.moexdataservice.event.ResponseFromKafka;
import com.dimas.moexdataservice.service.CurrencyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaResponseListener {
    private final CurrencyService currencyService;

    @EventListener
    public void handleMoexResponse(ResponseFromKafka event) {
        log.info("Received response from kafka: {}", event.getDataFromAggregator());
        this.currencyService.saveCurrencyDataList(
                event.getDataFromAggregator().getData()
        );
    }
}
