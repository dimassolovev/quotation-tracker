package com.dimas.moexdataservice.service;

import com.dimas.moexdataservice.model.entity.ClearingType;
import com.dimas.moexdataservice.model.entity.Currency;
import com.dimas.moexdataservice.model.entity.Security;
import com.dimas.moexdataservice.model.kafka.currency.CurrencyData;
import com.dimas.moexdataservice.model.kafka.currency.SecurityData;
import com.dimas.moexdataservice.repository.ClearingTypeRepository;
import com.dimas.moexdataservice.repository.CurrencyRepository;
import com.dimas.moexdataservice.repository.SecurityRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CurrencyService {
    private final ClearingTypeRepository clearingTypeRepository;
    private final SecurityRepository securityRepository;
    private final CurrencyRepository currencyRepository;

    private Map<String, ClearingType> clearingTypeMap;
    private Map<String, Security> securityMap;


    @PostConstruct
    public void init() {
        this.clearingTypeMap = this.clearingTypeRepository
                .findAll()
                .stream()
                .collect(
                        Collectors.toMap(
                                ClearingType::getClearing,
                                clearingType -> clearingType
                        )
                );

        this.securityMap = this.securityRepository
                .findAll()
                .stream()
                .collect(
                        Collectors.toMap(
                                Security::getPairCode,
                                security -> security
                        )
                );
    }

    @Transactional
    public void saveCurrencyDataList(List<CurrencyData> currencyDataList) {
        List<Currency> currencies = new ArrayList<>();


        for (CurrencyData currencyData : currencyDataList) {

            for (SecurityData securityData : currencyData.getSecurities()) {
                Currency currency = new Currency();

                if (!this.clearingTypeMap.containsKey(securityData.getClearing()))
                    this.clearingTypeMap.put(
                            securityData.getClearing(), this.clearingTypeRepository.findByClearing(securityData.getClearing())
                    );


                if (!this.securityMap.containsKey(securityData.getSecid()))
                    this.securityMap.put(
                            securityData.getSecid(), this.securityRepository.findByPairCode(securityData.getSecid())
                    );

                currency.setSecurity(this.securityMap.get(securityData.getSecid()));
                currency.setClearingType(this.clearingTypeMap.get(securityData.getClearing()));
                currency.setTradeTimestamp(
                        LocalDateTime.parse(
                                String.format(
                                        "%sT%s",
                                        securityData.getTradedate(),
                                        securityData.getTradetime()
                                )
                        )
                                .toEpochSecond(ZoneOffset.UTC)
                );
                currency.setRate(securityData.getRate());

                currencies.add(currency);
            }

            Currency currency = new Currency();
            currency.setSecurity(this.securityMap.get(currencyData.getCurrent().getSecid()));
            currency.setClearingType(this.clearingTypeRepository.findByClearing("nn"));
            currency.setTradeTimestamp(
                    LocalDateTime.parse(
                                    String.format(
                                            "%sT%s",
                                            currencyData.getCurrent().getTradedate(),
                                            currencyData.getCurrent().getTradetime()
                                    )
                            )
                            .toEpochSecond(ZoneOffset.UTC)
            );
            currency.setRate(currencyData.getCurrent().getRate());

            currencies.add(currency);
        }

        this.currencyRepository.saveAll(currencies);
    }
}
