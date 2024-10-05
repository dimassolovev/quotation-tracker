package com.dimas.stockdataaggregator.service.moex.api;

import com.dimas.stockdataaggregator.model.external.currency.CurrencyData;

public interface MoexCurrencyClient {
    CurrencyData getCurrency(String security, String from, String till, String mode,
                             String choice, Integer limit, String stringOrder);
}
