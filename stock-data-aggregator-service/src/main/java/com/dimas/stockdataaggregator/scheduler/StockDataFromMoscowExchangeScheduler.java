package com.dimas.stockdataaggregator.scheduler;

import com.dimas.stockdataaggregator.model.external.MoexData;
import com.dimas.stockdataaggregator.publisher.MoexResponseEventPublisher;
import com.dimas.stockdataaggregator.service.api.StockDataFromMoscowExchangeService;
import lombok.RequiredArgsConstructor;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class StockDataFromMoscowExchangeScheduler {
    private final StockDataFromMoscowExchangeService stockDataFromMoscowExchangeService;
    private final MoexResponseEventPublisher moexResponseEventPublisher;


//    @Scheduled(cron = "${scheduler.currency-moex-client.cron-expression}")
    @Scheduled(fixedRate = 20000)
    public void poll() {
        List<MoexData> moexDataList = stockDataFromMoscowExchangeService.getStockHistoryData();

        moexResponseEventPublisher.publishMoexResponseEvent(moexDataList);
    }
}
