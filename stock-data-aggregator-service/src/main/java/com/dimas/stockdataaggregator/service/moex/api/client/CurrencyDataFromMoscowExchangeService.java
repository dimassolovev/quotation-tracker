package com.dimas.stockdataaggregator.service.moex.api.client;

import com.dimas.stockdataaggregator.constant.property.CurrencyMoexClientProperty;
import com.dimas.stockdataaggregator.model.external.moex.currency.CurrencyData;
import com.dimas.stockdataaggregator.service.moex.api.MoscowExchangeClientService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that is guaranteed to receive non-empty, up-to-date data.
 */
@Service
@RequiredArgsConstructor
public class CurrencyDataFromMoscowExchangeService {
    private final CurrencyMoexClientProperty currencyMoexClientProperty;
    private final MoscowExchangeClientService moscowExchangeClientService;
    private final DateTimeFormatter dateTimeFormatter;

    public List<CurrencyData> getCurrencyData() {
        String currentDate = LocalDate.now().format(this.dateTimeFormatter);
        String pastDate = LocalDate.parse(currentDate, this.dateTimeFormatter).minusDays(10).format(this.dateTimeFormatter);
        List<CurrencyData> currencyDataList = new ArrayList<>();

        for (String security : this.currencyMoexClientProperty.getSecurities()) {
            CurrencyData currencyData;

            do {
                currencyData = moscowExchangeClientService.getCurrency(
                        String.format("%s/RUB", security),
                        pastDate,
                        currentDate,
                        this.currencyMoexClientProperty.getMetaMode(),
                        this.currencyMoexClientProperty.getChoice(),
                        this.currencyMoexClientProperty.getLimit(),
                        this.currencyMoexClientProperty.getStringOrder()
                );

                currentDate = LocalDate.parse(currentDate, this.dateTimeFormatter).minusDays(1).format(this.dateTimeFormatter);
            }
            while (currencyData.getSecurities().isEmpty() && currencyData.getCurrent() == null);

            currencyDataList.add(currencyData);
        }

        return currencyDataList;
    }
}
