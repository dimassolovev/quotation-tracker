package com.dimas.moexdataservice.service;

import com.dimas.moexdataservice.constant.Message;
import com.dimas.moexdataservice.exception.CurrencyNotFoundByDateException;
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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

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
            Optional<List<Currency>> currencies = this.currencyRepository.findByTradeDate(localDate);

            return new DataDto<>(currencies
                    .orElseThrow(() -> new CurrencyNotFoundByDateException(Message.CURRENCY_NOT_FOUND))
                    .stream()
                    .map(this.currencyDataDtoMapper::toDto)
                    .toList()
            );
        } catch (DateTimeParseException exception) {
            throw new IncorrectDateFormat(Message.INCORRECT_DATE_FORMAT);
        }
    }
}
