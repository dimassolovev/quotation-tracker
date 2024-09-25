package com.dimas.stockdataaggregator.util.scraper;

import com.dimas.stockdataaggregator.model.external.currency.CurrencyData;
import com.dimas.stockdataaggregator.model.external.currency.Security;
import com.dimas.stockdataaggregator.model.external.currency.Current;
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
public class CsvCurrencyDataFromMoscowExchangeService {
    private final CsvMapper csvMapper;
    private final CsvSchema csvSchema;
    private final StringTool stringTool;

    public CurrencyData parseCurrencyDataFromMoscowExchange(String csvData) throws IOException {
        csvData = stringTool.filterEmptyLines(csvData);

        List<Security> securities = this.parseSecurities(csvData);
        Current current = this.parseCurrent(csvData);

        return new CurrencyData(securities, current);
    }

    private List<Security> parseSecurities(String csvData) throws IOException {
        csvData = stringTool.removeSection(csvData, "securities");
        csvData = stringTool.removeUnnecessarySectionsAfterSection(csvData, "securities.cursor");
        StringReader stringReader = new StringReader(csvData);

        MappingIterator<Security> securitiesIterator = csvMapper.readerFor(Security.class)
                .with(csvSchema)
                .readValues(stringReader);

        return securitiesIterator.readAll();
    }

    private Current parseCurrent(String csvData) throws IOException {
        csvData = stringTool.removeUnnecessarySectionsBeforeSection(csvData, "securities.current");
        csvData = stringTool.removeSection(csvData, "securities.current");

        StringReader stringReader = new StringReader(csvData);


        MappingIterator<Current> currentIterator = csvMapper.readerFor(Current.class)
                .with(csvSchema)
                .readValues(stringReader);

        return currentIterator.readAll().get(0);
    }
}
