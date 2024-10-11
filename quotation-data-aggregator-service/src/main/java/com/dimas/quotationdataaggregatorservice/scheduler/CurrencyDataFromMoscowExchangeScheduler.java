package com.dimas.quotationdataaggregatorservice.scheduler;

import com.dimas.quotationdataaggregatorservice.model.external.DataFromExternalServices;
import com.dimas.quotationdataaggregatorservice.model.external.moex.currency.CurrencyData;
import com.dimas.quotationdataaggregatorservice.publisher.MoexResponseEventPublisher;
import com.dimas.quotationdataaggregatorservice.service.moex.api.client.CurrencyDataFromMoscowExchangeService;

import lombok.RequiredArgsConstructor;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Planner for queries in moex
 */
@Component
@EnableScheduling
@RequiredArgsConstructor
public class CurrencyDataFromMoscowExchangeScheduler {
    private final CurrencyDataFromMoscowExchangeService currencyDataFromMoscowExchangeService;
    private final MoexResponseEventPublisher moexResponseEventPublisher;

    /**
     * The method requests data and publishes it to events.
     */
//    @Scheduled(cron = "${scheduler.currency-moex-client.cron-expression}")
    @Scheduled(fixedRate = 60000)
    public void poll() {
        DataFromExternalServices<List<CurrencyData>> dataFromExternalServices = new DataFromExternalServices<>();
        List<CurrencyData> currencyDataList = this.currencyDataFromMoscowExchangeService.getCurrencyData();

        dataFromExternalServices.setData(currencyDataList);

        this.moexResponseEventPublisher.publishResponseFromSourcesEvent(dataFromExternalServices);
    }
}
