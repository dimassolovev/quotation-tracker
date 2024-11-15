package com.dimas.quotationdataaggregatorservice.util.scraper;

import com.dimas.quotationdataaggregatorservice.model.external.moex.currency.CurrencyData;
import com.dimas.quotationdataaggregatorservice.model.external.moex.currency.Current;
import com.dimas.quotationdataaggregatorservice.model.external.moex.currency.Security;
import com.dimas.quotationdataaggregatorservice.util.tool.StringTool;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CsvCurrencyDataFromMoscowExchangeScraper {
    private final CsvMapper csvMapper;
    private final CsvSchema csvSchema;
    private final StringTool stringTool;

    public CurrencyData parseCurrencyDataFromMoscowExchange(String csvData) throws IOException {
        csvData = this.stringTool.filterEmptyLines(csvData);

        var securities = this.parseSecurities(csvData);
        Current current = this.parseCurrent(csvData);

        return new CurrencyData(securities, current);
    }

    private List<Security> parseSecurities(String csvData) throws IOException {
        csvData = this.stringTool.removeSection(csvData, "securities");
        csvData = this.stringTool.removeUnnecessarySectionsAfterSection(csvData, "securities.cursor");
        var stringReader = new StringReader(csvData);

        MappingIterator<Security> securitiesIterator = this.csvMapper.readerFor(Security.class)
                .with(this.csvSchema)
                .readValues(stringReader);

        return securitiesIterator.readAll();
    }

    private Current parseCurrent(String csvData) throws IOException {
        csvData = this.stringTool.removeUnnecessarySectionsBeforeSection(csvData, "securities.current");
        csvData = this.stringTool.removeSection(csvData, "securities.current");

        var stringReader = new StringReader(csvData);


        MappingIterator<Current> currentIterator = this.csvMapper.readerFor(Current.class)
                .with(this.csvSchema)
                .readValues(stringReader);

        return currentIterator.readAll().get(0);
    }
}
