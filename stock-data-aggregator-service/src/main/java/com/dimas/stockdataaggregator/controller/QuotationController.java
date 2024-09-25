package com.dimas.stockdataaggregator.controller;

import com.dimas.stockdataaggregator.model.external.currency.CurrencyData;
import com.dimas.stockdataaggregator.model.external.stock.StockHistoryData;

import com.dimas.stockdataaggregator.service.api.MoscowExchangeClientService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/stock")
    public StockHistoryData getStock(
            @RequestParam(required = false, name = "limit") Integer limit,
            @RequestParam(required = false, name = "sort_column") String sortColumn,
            @RequestParam(required = false, name = "sort_order") String sortOrder,
            @RequestParam(required = false, name = "lang") String language,
            @RequestParam(required = false, name = "start") Integer start,
            @RequestParam(required = false, name = "numtrades") Integer numTrades,
            @RequestParam(required = false, name = "iss.meta") String mode,
            @RequestParam(required = false, name = "date") String date
    ) {

        return moscowExchangeClientService.getTradeHistory(
                limit, sortColumn, sortOrder, language, start, numTrades, mode, date
        );
    }
}
