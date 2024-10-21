package com.dimas.quotationdataaggregatorservice.event;

import com.dimas.quotationdataaggregatorservice.model.external.moex.currency.CurrencyData;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.List;

@Getter
public class CurrencyResponseFromMoexEvent extends ApplicationEvent {
    private final List<CurrencyData> currenciesList;
    private final String topicName;

    public CurrencyResponseFromMoexEvent(Object source, List<CurrencyData> currenciesList, String topicName) {
        super(source);
        this.currenciesList = currenciesList;
        this.topicName = topicName;
    }
}