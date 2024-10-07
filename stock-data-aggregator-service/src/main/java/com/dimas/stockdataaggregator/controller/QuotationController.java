package com.dimas.stockdataaggregator.controller;

import com.dimas.stockdataaggregator.model.external.moex.currency.CurrencyData;
import com.dimas.stockdataaggregator.service.moex.api.MoscowExchangeClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quotation")
@RequiredArgsConstructor
public class QuotationController {
    private final MoscowExchangeClientService moscowExchangeClientService;

    @GetMapping("/currency/{first_currency}/{second_currency}")
    public CurrencyData getQuote(
            @PathVariable("first_currency") String firstCurrency,
            @PathVariable("second_currency") String secondCurrency,
            @RequestParam(required = false, name = "from") String from,
            @RequestParam(required = false, name = "till") String till,
            @RequestParam(required = false, name = "iss.meta") String mode,
            @RequestParam(required = false, name = "iss.only") String choice,
            @RequestParam(required = false, name = "limit") Integer limit,
            @RequestParam(required = false, name = "sort_order") String stringOrder) {

        return moscowExchangeClientService.getCurrency(
                String.format("%s/%s", firstCurrency, secondCurrency),
                from, till, mode, choice, limit, stringOrder
        );
    }
}
