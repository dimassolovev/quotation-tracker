package com.dimas.stockdataaggregator.service.moex.api;

import com.dimas.stockdataaggregator.client.MoscowExchangeClient;
import com.dimas.stockdataaggregator.constant.Message;
import com.dimas.stockdataaggregator.exception.ParseObjectException;
import com.dimas.stockdataaggregator.model.external.moex.currency.CurrencyData;
import com.dimas.stockdataaggregator.util.scraper.CsvCurrencyDataFromMoscowExchangeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class MoscowExchangeClientService implements MoexCurrencyClient {
    private final MoscowExchangeClient moscowExchangeClient;
    private final CsvCurrencyDataFromMoscowExchangeService csvCurrencyDataFromMoscowExchangeService;

    @Override
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
}
