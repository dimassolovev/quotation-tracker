package com.dimas.moexdataservice.service;

import com.dimas.moexdataservice.mapper.CurrentDataMapper;
import com.dimas.moexdataservice.mapper.SecurityDataMapper;
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

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CurrencyService {
    private final ClearingTypeRepository clearingTypeRepository;
    private final SecurityRepository securityRepository;
    private final CurrencyRepository currencyRepository;

    private final SecurityDataMapper securityDataMapper;
    private final CurrentDataMapper currentDataMapper;


    @PostConstruct
    public void init() {
        Map<String, ClearingType> clearingTypeMap = this.clearingTypeRepository
                .findAll()
                .stream()
                .collect(
                        Collectors.toMap(
                                ClearingType::getClearing,
                                clearingType -> clearingType
                        )
                );

        Map<String, Security> securityMap = this.securityRepository
                .findAll()
                .stream()
                .collect(
                        Collectors.toMap(
                                Security::getPairCode,
                                security -> security
                        )
                );

        this.securityDataMapper.setSecurityMap(securityMap);
        this.securityDataMapper.setClearingTypeMap(clearingTypeMap);
        this.currentDataMapper.setSecurityMap(securityMap);
        this.currentDataMapper.setClearingTypeMap(clearingTypeMap);
    }

    @Transactional
    public void saveCurrencyDataList(List<CurrencyData> currencyDataList) {
        List<Currency> currencies = new ArrayList<>();


        for (CurrencyData currencyData : currencyDataList) {

            for (SecurityData securityData : currencyData.getSecurities()) {
                Currency currency = this.securityDataMapper.toEntity(securityData);
                currencies.add(currency);
            }

            Currency currency = this.currentDataMapper.toEntity(currencyData.getCurrent());
            currencies.add(currency);
        }

        this.currencyRepository.saveAll(currencies);
    }
}
