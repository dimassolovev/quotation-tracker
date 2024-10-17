package com.dimas.moexdataservice.mapper.kafka;

import com.dimas.moexdataservice.mapper.EntityMappable;
import com.dimas.moexdataservice.model.entity.currency.ClearingType;
import com.dimas.moexdataservice.model.entity.currency.Currency;
import com.dimas.moexdataservice.model.entity.currency.Security;
import com.dimas.moexdataservice.model.kafka.currency.SecurityData;

import lombok.Setter;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;


@Component
@Setter
public class SecurityDataMapper implements EntityMappable<Currency, SecurityData> {

    private Map<String, ClearingType> clearingTypeMap;
    private Map<String, Security> securityMap;

    @Override
    public Currency toEntity(SecurityData dto) {
        Currency currency = new Currency();

        currency.setSecurity(this.securityMap.get(dto.getSecid()));
        currency.setClearingType(this.clearingTypeMap.get(dto.getClearing()));
        currency.setTradeTime(LocalTime.parse(dto.getTradetime()));
        currency.setTradeDate(LocalDate.parse(dto.getTradedate()));
        currency.setRate(dto.getRate());

        return currency;
    }
}
