package com.dimas.quotationdataaggregatorservice.service.moex.api;

import com.dimas.quotationdataaggregatorservice.model.external.moex.currency.CurrencyData;

public interface MoexCurrencyClient {
    CurrencyData getCurrency(String security,
                             String from,
                             String till,
                             String mode,
                             String choice,
                             Integer limit,
                             String stringOrder
    );
}
