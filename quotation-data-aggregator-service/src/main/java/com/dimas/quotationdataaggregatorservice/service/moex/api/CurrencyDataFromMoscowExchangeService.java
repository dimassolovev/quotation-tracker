package com.dimas.quotationdataaggregatorservice.service.moex.api;

import com.dimas.quotationdataaggregatorservice.model.external.moex.currency.CurrencyData;

import java.util.List;

public interface CurrencyDataFromMoscowExchangeService {
    List<CurrencyData> getCurrencyData();
}
