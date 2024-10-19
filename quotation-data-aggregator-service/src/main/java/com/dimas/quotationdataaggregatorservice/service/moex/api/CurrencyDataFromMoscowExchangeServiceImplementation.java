package com.dimas.quotationdataaggregatorservice.service.moex.api;

import com.dimas.quotationdataaggregatorservice.client.MoscowExchangeClient;
import com.dimas.quotationdataaggregatorservice.constant.property.CurrencyMoexClientProperty;
import com.dimas.quotationdataaggregatorservice.model.external.moex.currency.CurrencyData;
import com.dimas.quotationdataaggregatorservice.util.scraper.CsvCurrencyDataFromMoscowExchangeScraper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CurrencyDataFromMoscowExchangeServiceImplementation implements CurrencyDataFromMoscowExchangeService {
    private final CurrencyMoexClientProperty currencyMoexClientProperty;
    private final DateTimeFormatter dateTimeFormatter;
    private final MoscowExchangeClient moscowExchangeClient;
    private final CsvCurrencyDataFromMoscowExchangeScraper csvCurrencyDataFromMoscowExchangeScraper;

    @Override
    public List<CurrencyData> getCurrencyData() {
        String currentDate = LocalDate.now().format(this.dateTimeFormatter);
        String pastDate = LocalDate.parse(currentDate, this.dateTimeFormatter).minusDays(6).format(this.dateTimeFormatter);
        List<CurrencyData> currencyDataList = new ArrayList<>();

        for (String security : this.currencyMoexClientProperty.getSecurities()) {
            CurrencyData currencyData = null;
            do {
                try {
                    String response = this.moscowExchangeClient.getCurrency(
                            String.format("%s/RUB", security),
                            pastDate,
                            currentDate,
                            this.currencyMoexClientProperty.getMetaMode(),
                            this.currencyMoexClientProperty.getChoice(),
                            this.currencyMoexClientProperty.getLimit(),
                            this.currencyMoexClientProperty.getStringOrder()
                    );

                    currencyData = this.csvCurrencyDataFromMoscowExchangeScraper.parseCurrencyDataFromMoscowExchange(response);

                } catch (Exception exception) {
                    log.error(exception.getMessage(), exception);
                    break;
                }

                currentDate = LocalDate.parse(currentDate, this.dateTimeFormatter).minusDays(1).format(this.dateTimeFormatter);
            }
            while (currencyData.getSecurities().isEmpty() && currencyData.getCurrent() == null);

            currencyDataList.add(currencyData);
        }

        return currencyDataList;
    }
}
