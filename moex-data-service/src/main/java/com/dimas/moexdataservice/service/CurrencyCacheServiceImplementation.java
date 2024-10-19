package com.dimas.moexdataservice.service;

import com.dimas.moexdataservice.constant.Message;
import com.dimas.moexdataservice.exception.IncorrectDateFormat;
import com.dimas.moexdataservice.mapper.dto.CurrencyDataDtoMapper;
import com.dimas.moexdataservice.model.dto.currency.CurrencyDataDto;
import com.dimas.moexdataservice.model.dto.currency.DataDto;
import com.dimas.moexdataservice.model.entity.currency.Currency;
import com.dimas.moexdataservice.repository.CurrencyRepository;
import com.dimas.moexdataservice.util.DateValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyCacheServiceImplementation implements CurrencyCacheService {
    private final CurrencyDataDtoMapper currencyDataDtoMapper;
    private final DateTimeFormatter dateTimeFormatter;
    private final CurrencyRepository currencyRepository;
    private final DateValidator dateValidator;

    @Override
    @Cacheable(key = "#date",
            value = "currency-moex-data",
            condition = "@dateValidator.isValid(#date)",
            unless = "#result == null"
    )
    public DataDto<List<CurrencyDataDto>> find(String date) {
        try {
            LocalDate localDate = LocalDate.parse(date, this.dateTimeFormatter);
            List<Currency> currencies = this.currencyRepository.findByTradeDate(Date.valueOf(localDate));

            return new DataDto<>(
                    currencies
                    .stream()
                    .map(this.currencyDataDtoMapper::toDto)
                    .toList()
            );
        } catch (DateTimeParseException exception) {
            throw new IncorrectDateFormat(Message.INCORRECT_DATE_FORMAT);
        }
    }

    @Override
    @Cacheable(key = "#date.concat(#pairCode)",
            value = "currency-moex-data",
            condition = "@dateValidator.isValid(#date)",
            unless = "#result == null"
    )
    public DataDto<List<CurrencyDataDto>> findByPairCode(String date, String pairCode) {
        try {
            LocalDate localDate = LocalDate.parse(date, this.dateTimeFormatter);
            List<Currency> currencies = this.currencyRepository.findByFiltration(Date.valueOf(localDate), pairCode);

            List<CurrencyDataDto> dtoCurrencies = currencies
                    .stream()
                    .map(this.currencyDataDtoMapper::toDto)
                    .toList();

            return dtoCurrencies.isEmpty() ? null : new DataDto<>(dtoCurrencies);

        } catch (DateTimeParseException exception) {
            throw new IncorrectDateFormat(Message.INCORRECT_DATE_FORMAT);
        }
    }
}