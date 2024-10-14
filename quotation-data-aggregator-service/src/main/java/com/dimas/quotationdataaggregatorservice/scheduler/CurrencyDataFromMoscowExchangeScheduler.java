package com.dimas.quotationdataaggregatorservice.scheduler;

import com.dimas.quotationdataaggregatorservice.constant.property.KafkaConfigurationProperty;
import com.dimas.quotationdataaggregatorservice.model.external.DataFromExternalServices;
import com.dimas.quotationdataaggregatorservice.model.external.moex.currency.CurrencyData;
import com.dimas.quotationdataaggregatorservice.publisher.MoexCurrencyResponseEventPublisher;
import com.dimas.quotationdataaggregatorservice.service.moex.api.client.CurrencyDataFromMoscowExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class CurrencyDataFromMoscowExchangeScheduler {
    private final CurrencyDataFromMoscowExchangeService currencyDataFromMoscowExchangeService;
    private final MoexCurrencyResponseEventPublisher moexCurrencyResponseEventPublisher;
    private final KafkaConfigurationProperty kafkaConfigurationProperty;

//    @Scheduled(cron = "${scheduler.currency-moex-client.cron-expression}")
    @Scheduled(fixedRate = 60000)
    public void poll() {
        List<CurrencyData> currencyDataList = this.currencyDataFromMoscowExchangeService.getCurrencyData();

        this.moexCurrencyResponseEventPublisher.publishResponseFromSourcesEvent(
                new DataFromExternalServices<>(currencyDataList),
                kafkaConfigurationProperty.getTopic()
        );
    }
}
