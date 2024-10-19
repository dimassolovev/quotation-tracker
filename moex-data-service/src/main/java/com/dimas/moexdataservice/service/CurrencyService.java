package com.dimas.moexdataservice.service;

import com.dimas.moexdataservice.model.kafka.currency.CurrencyData;

public interface CurrencyService {
    void save(CurrencyData currencyData);
}
