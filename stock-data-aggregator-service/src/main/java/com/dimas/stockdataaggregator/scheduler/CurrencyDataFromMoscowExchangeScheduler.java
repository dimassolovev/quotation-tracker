package com.dimas.stockdataaggregator.scheduler;

import com.dimas.stockdataaggregator.model.external.MoexData;
import com.dimas.stockdataaggregator.publisher.MoexResponseEventPublisher;
import com.dimas.stockdataaggregator.service.moex.api.client.CurrencyDataFromMoscowExchangeService;
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
    private final MoexResponseEventPublisher moexResponseEventPublisher;


//    @Scheduled(cron = "${scheduler.currency-moex-client.cron-expression}")
    @Scheduled(fixedRate = 60000)
    public void poll() {
        List<MoexData> moexDataList = currencyDataFromMoscowExchangeService.getCurrencyData();

        moexResponseEventPublisher.publishMoexResponseEvent(moexDataList);
    }
}
