package com.dimas.stockdataaggregator.util.scraper;

import com.dimas.stockdataaggregator.model.external.stock.Trade;
import com.dimas.stockdataaggregator.model.external.stock.StockHistoryData;
import com.dimas.stockdataaggregator.util.tool.StringTool;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CsvPromotionDataFromMoscowExchangeService {
    private final CsvMapper csvMapper;
    private final CsvSchema csvSchema;
    private final StringTool stringTool;

    public StockHistoryData parseTradeHistory(String csvData) throws IOException {
        csvData = stringTool.filterEmptyLines(csvData);
        List<Trade> trades = this.parseTrade(csvData);

        return new StockHistoryData(trades);
    }

    private List<Trade> parseTrade(String csvData) throws IOException {
        csvData = stringTool.removeSection(csvData, "history");
        csvData = stringTool.removeUnnecessarySectionsAfterSection(csvData, "history.cursor");
        StringReader stringReader = new StringReader(csvData);

        MappingIterator<Trade> securitiesIterator = csvMapper.readerFor(Trade.class)
                .with(csvSchema)
                .readValues(stringReader);

        return securitiesIterator.readAll();
    }
}
