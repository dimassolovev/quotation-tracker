package com.dimas.moexdataservice.mapper.dto;

import com.dimas.moexdataservice.mapper.DtoMappable;
import com.dimas.moexdataservice.model.dto.currency.CurrencyDataDto;
import com.dimas.moexdataservice.model.entity.currency.Currency;
import org.springframework.stereotype.Component;

@Component
public class CurrencyDataDtoMapper implements DtoMappable<Currency, CurrencyDataDto> {

    @Override
    public CurrencyDataDto toDto(Currency entity) {
        CurrencyDataDto currencyDataDto = new CurrencyDataDto();

        currencyDataDto.setClearing(
                entity.getClearingType().getClearing()
        );

        currencyDataDto.setSecid(
                entity.getSecurity().getPairCode()
        );

        currencyDataDto.setRate(
                entity.getRate()
        );

        currencyDataDto.setTradetime(
                entity.getTradeTime().toString()
        );

        currencyDataDto.setTradedate(
                entity.getTradeDate().toString()
        );

        return currencyDataDto;
    }
}
