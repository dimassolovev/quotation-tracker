package com.dimas.moexdataservice.mapper;

import com.dimas.moexdataservice.model.entity.ClearingType;
import com.dimas.moexdataservice.model.entity.Currency;
import com.dimas.moexdataservice.model.entity.Security;
import com.dimas.moexdataservice.model.kafka.currency.CurrentData;

import lombok.Setter;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import java.util.Map;

@Component
@Setter
public class CurrentDataMapper implements EntityMappable<Currency, CurrentData> {

    private Map<String, ClearingType> clearingTypeMap;
    private Map<String, Security> securityMap;

    @Override
    public Currency toEntity(CurrentData dto) {
        Currency currency = new Currency();

        currency.setSecurity(this.securityMap.get(dto.getSecid()));
        currency.setClearingType(this.clearingTypeMap.get("nn"));
        currency.setTradeTimestamp(
                LocalDateTime.parse(
                                String.format(
                                        "%sT%s",
                                        dto.getTradedate(),
                                        dto.getTradetime()
                                )
                        )
                        .toEpochSecond(ZoneOffset.UTC)
        );
        currency.setRate(dto.getRate());

        return currency;
    }
}
