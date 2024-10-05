package com.dimas.stockdataaggregator.service.moex.api;

import com.dimas.stockdataaggregator.model.external.stock.StockHistoryData;

public interface MoexStockClient {
    StockHistoryData getTradeHistory(Integer limit, String sortColumn, String sortOrder,
                                     String language, Integer start,
                                     Integer numTrades, String mode, String date);
}
