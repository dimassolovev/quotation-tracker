package com.dimas.stockdataaggregator.service.api;

import com.dimas.stockdataaggregator.constant.property.CurrencyMoexClientProperty;
import com.dimas.stockdataaggregator.model.external.MoexData;
import com.dimas.stockdataaggregator.model.external.currency.CurrencyData;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyDataFromMoscowExchangeService {
    private final CurrencyMoexClientProperty currencyMoexClientProperty;
    private final MoscowExchangeClientService moscowExchangeClientService;
    private final DateTimeFormatter dateTimeFormatter;

    public List<MoexData> getCurrencyData() {
        String currentDate = LocalDate.now().format(dateTimeFormatter);
        String pastDate = LocalDate.parse(currentDate, dateTimeFormatter).minusDays(10).format(dateTimeFormatter);
        List<MoexData> currencyDataList = new ArrayList<>();

        for (String security: currencyMoexClientProperty.getSecurities()) {
            CurrencyData currencyData;

            do {
                currencyData = moscowExchangeClientService.getCurrency(
                        String.format("%s/RUB", security),
                        pastDate,
                        currentDate,
                        currencyMoexClientProperty.getMetaMode(),
                        currencyMoexClientProperty.getChoice(),
                        currencyMoexClientProperty.getLimit(),
                        currencyMoexClientProperty.getStringOrder()
                );

                currentDate = LocalDate.parse(currentDate, dateTimeFormatter).minusDays(1).format(dateTimeFormatter);
            }
            while (currencyData.getSecurities().isEmpty() && currencyData.getCurrent() == null);

            currencyDataList.add(currencyData);
        }

        return currencyDataList;
    }
}
