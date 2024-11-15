package com.dimas.moexdataservice.service.implementation;

import com.dimas.moexdataservice.mapper.kafka.CurrentDataMapper;
import com.dimas.moexdataservice.mapper.kafka.SecurityDataMapper;
import com.dimas.moexdataservice.model.entity.currency.ClearingType;
import com.dimas.moexdataservice.model.entity.currency.Currency;
import com.dimas.moexdataservice.model.entity.currency.Security;
import com.dimas.moexdataservice.model.kafka.currency.CurrencyData;
import com.dimas.moexdataservice.model.kafka.currency.SecurityData;
import com.dimas.moexdataservice.repository.ClearingTypeRepository;
import com.dimas.moexdataservice.repository.CurrencyRepository;
import com.dimas.moexdataservice.repository.SecurityRepository;
import com.dimas.moexdataservice.service.CurrencyService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CurrencyServiceImplementation implements CurrencyService {
    private final ClearingTypeRepository clearingTypeRepository;
    private final SecurityRepository securityRepository;
    private final CurrencyRepository currencyRepository;
    private final SecurityDataMapper securityDataMapper;
    private final CurrentDataMapper currentDataMapper;

    @PostConstruct
    public void init() {
        var clearingTypeMap = this.clearingTypeRepository
                .findAll()
                .stream()
                .collect(
                        Collectors.toMap(
                                ClearingType::getClearing,
                                clearingType -> clearingType
                        )
                );

        var securityMap = this.securityRepository
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

    @Override
    @Transactional
    public void save(CurrencyData currencyData) {
        List<Currency> currencies = new ArrayList<>();

        for (SecurityData securityData : currencyData.getSecurities()) {
            var currency = this.securityDataMapper.toEntity(securityData);
            currencies.add(currency);
        }

        var currency = this.currentDataMapper.toEntity(currencyData.getCurrent());
        currencies.add(currency);

        this.currencyRepository.saveAll(currencies);
    }
}
