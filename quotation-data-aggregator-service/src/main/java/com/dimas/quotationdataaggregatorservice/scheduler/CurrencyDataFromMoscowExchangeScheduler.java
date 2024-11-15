package com.dimas.quotationdataaggregatorservice.scheduler;

import com.dimas.quotationdataaggregatorservice.constant.property.KafkaConfigurationProperty;
import com.dimas.quotationdataaggregatorservice.model.external.moex.currency.CurrencyData;
import com.dimas.quotationdataaggregatorservice.publisher.MoexCurrencyResponseEventPublisher;
import com.dimas.quotationdataaggregatorservice.service.moex.api.DataFromMoscowExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CurrencyDataFromMoscowExchangeScheduler implements Scheduler {
    private final DataFromMoscowExchangeService<List<CurrencyData>> currencyDataFromMoscowExchangeService;
    private final MoexCurrencyResponseEventPublisher moexCurrencyResponseEventPublisher;
    private final KafkaConfigurationProperty kafkaConfigurationProperty;

    @Scheduled(cron = "${scheduler.currency-moex-client.cron-expression}")
    public void poll() {
        var currencyDataList = this.currencyDataFromMoscowExchangeService.getCurrencyData();

        this.moexCurrencyResponseEventPublisher.publishResponseFromSourcesEvent(
                currencyDataList,
                kafkaConfigurationProperty.getTopic()
        );
    }
}
