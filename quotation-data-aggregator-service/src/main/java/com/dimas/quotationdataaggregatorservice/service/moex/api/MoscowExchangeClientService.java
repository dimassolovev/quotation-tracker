package com.dimas.quotationdataaggregatorservice.service.moex.api;

import com.dimas.quotationdataaggregatorservice.client.MoscowExchangeClient;
import com.dimas.quotationdataaggregatorservice.constant.Message;
import com.dimas.quotationdataaggregatorservice.exception.ParseObjectException;
import com.dimas.quotationdataaggregatorservice.model.external.moex.currency.CurrencyData;
import com.dimas.quotationdataaggregatorservice.util.scraper.CsvCurrencyDataFromMoscowExchangeService;

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
        String response = this.moscowExchangeClient.getCurrency(
                security, from, till, mode, choice, limit, stringOrder
        );

        try {
            return this.csvCurrencyDataFromMoscowExchangeService.parseCurrencyDataFromMoscowExchange(response);
        }
        catch (IOException e) {
            log.error(e.getMessage());
        }
        throw new ParseObjectException(Message.PARSE_OBJECT_EXCEPTION);
    }
}
