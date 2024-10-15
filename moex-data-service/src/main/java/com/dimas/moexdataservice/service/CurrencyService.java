package com.dimas.moexdataservice.service;

import com.dimas.moexdataservice.model.entity.ClearingType;
import com.dimas.moexdataservice.model.entity.Currency;
import com.dimas.moexdataservice.model.entity.Security;
import com.dimas.moexdataservice.model.kafka.currency.CurrencyData;
import com.dimas.moexdataservice.model.kafka.currency.SecurityData;
import com.dimas.moexdataservice.repository.ClearingTypeRepository;
import com.dimas.moexdataservice.repository.CurrencyRepository;
import com.dimas.moexdataservice.repository.SecurityRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CurrencyService {
    private final ClearingTypeRepository clearingTypeRepository;
    private final SecurityRepository securityRepository;
    private final CurrencyRepository currencyRepository;

    @Transactional
    public void saveCurrencyDataList(List<CurrencyData> currencyDataList) {
        List<Currency> currencies = new ArrayList<>();

        Map<String, ClearingType> clearingTypeMap = new HashMap<>();
        Map<String, Security> securityMap = new HashMap<>();


        for (CurrencyData currencyData : currencyDataList) {

            for (SecurityData securityData : currencyData.getSecurities()) {
                Currency currency = new Currency();

                if (!clearingTypeMap.containsKey(securityData.getClearing()))
                    clearingTypeMap.put(
                            securityData.getClearing(), this.clearingTypeRepository.findByClearing(securityData.getClearing())
                    );


                if (!securityMap.containsKey(securityData.getSecid()))
                    securityMap.put(
                            securityData.getSecid(), this.securityRepository.findByPairCode(securityData.getSecid())
                    );

                currency.setSecurity(securityMap.get(securityData.getSecid()));
                currency.setClearingType(clearingTypeMap.get(securityData.getClearing()));
                currency.setTradedate(LocalDate.parse(securityData.getTradedate()));
                currency.setTradetime(LocalTime.parse(securityData.getTradetime()));
                currency.setRate(securityData.getRate());

                currencies.add(currency);
            }

            Currency currency = new Currency();
            currency.setSecurity(securityMap.get(currencyData.getCurrent().getSecid()));
            currency.setClearingType(this.clearingTypeRepository.findByClearing("nn"));
            currency.setTradedate(LocalDate.parse(currencyData.getCurrent().getTradedate()));
            currency.setTradetime(LocalTime.parse(currencyData.getCurrent().getTradetime()));
            currency.setRate(currencyData.getCurrent().getRate());

            currencies.add(currency);
        }

        this.currencyRepository.saveAll(currencies);
    }
}
