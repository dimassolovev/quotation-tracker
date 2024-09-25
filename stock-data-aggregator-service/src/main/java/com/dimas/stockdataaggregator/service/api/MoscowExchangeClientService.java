package com.dimas.stockdataaggregator.service.api;

import com.dimas.stockdataaggregator.client.MoscowExchangeClient;
import com.dimas.stockdataaggregator.constant.Message;
import com.dimas.stockdataaggregator.exception.ParseObjectException;
import com.dimas.stockdataaggregator.model.external.currency.CurrencyData;
import com.dimas.stockdataaggregator.model.external.stock.StockHistoryData;

import com.dimas.stockdataaggregator.util.scraper.CsvCurrencyDataFromMoscowExchangeService;
import com.dimas.stockdataaggregator.util.scraper.CsvPromotionDataFromMoscowExchangeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class MoscowExchangeClientService {
    private final MoscowExchangeClient moscowExchangeClient;
    private final CsvCurrencyDataFromMoscowExchangeService csvCurrencyDataFromMoscowExchangeService;
    private final CsvPromotionDataFromMoscowExchangeService csvPromotionDataFromMoscowExchangeService;

    public CurrencyData getCurrency(String security, String from, String till, String mode, String choice, Integer limit, String stringOrder) {
        String response = moscowExchangeClient.getCurrency(
                security, from, till, mode, choice, limit, stringOrder
        );

        try {
            return csvCurrencyDataFromMoscowExchangeService.parseCurrencyDataFromMoscowExchange(response);
        }
        catch (IOException e) {
            log.error(e.getMessage());
        }
        throw new ParseObjectException(Message.PARSE_OBJECT_EXCEPTION);
    }

    public StockHistoryData getTradeHistory(
            Integer limit, String sortColumn, String sortOrder, String language, Integer start,
            Integer numTrades, String mode, String date) {

        String response = moscowExchangeClient.getTradeHistory(
                 limit, sortColumn, sortOrder, language, start, numTrades, mode, date
        );

        try {
            return csvPromotionDataFromMoscowExchangeService.parseTradeHistory(response);
        }
        catch (IOException e) {
            log.error(e.getMessage());
        }
        throw new ParseObjectException(Message.PARSE_OBJECT_EXCEPTION);
    }
}
