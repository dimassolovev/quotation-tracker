package com.dimas.stockdataaggregator.service.api;

import com.dimas.stockdataaggregator.constant.property.StockMoexClientProperty;
import com.dimas.stockdataaggregator.model.external.MoexData;
import com.dimas.stockdataaggregator.model.external.stock.StockHistoryData;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockDataFromMoscowExchangeService {
    private final MoscowExchangeClientService moscowExchangeClientService;
    private final DateTimeFormatter dateTimeFormatter;
    private final StockMoexClientProperty stockMoexClientProperty;

    public List<MoexData> getStockHistoryData() {
        String currentDate = LocalDate.now().format(dateTimeFormatter);
        StockHistoryData tradeHistoryData;

        do {
            tradeHistoryData = moscowExchangeClientService.getTradeHistory(
                    stockMoexClientProperty.getLimit(),
                    stockMoexClientProperty.getSortColumn(),
                    stockMoexClientProperty.getSortOrder(),
                    stockMoexClientProperty.getLanguage(),
                    stockMoexClientProperty.getStart(),
                    stockMoexClientProperty.getNumTrades(),
                    stockMoexClientProperty.getMetaMode(),
                    currentDate
            );
            currentDate = LocalDate.parse(currentDate, dateTimeFormatter).minusDays(1).format(dateTimeFormatter);
        }
        while (tradeHistoryData.getTrades().isEmpty());

        return List.of(tradeHistoryData);
    }
}
