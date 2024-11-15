package com.dimas.quotationdataaggregatorservice.mapper;

import com.dimas.quotationdataaggregatorservice.model.external.moex.currency.CurrencyData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CurrencyDataMapper implements Mappable<CurrencyData, String> {
    private final ObjectMapper objectMapper;

    @Override
    public CurrencyData toEntity(String dto) {
        try {
            return this.objectMapper.readValue(dto, CurrencyData.class);
        } catch (JsonProcessingException exception) {
            log.error(exception.getMessage(), exception);
        }

        return null;
    }

    @Override
    public String toDto(CurrencyData entity) {
        try {
            return this.objectMapper.writeValueAsString(entity);
        } catch (JsonProcessingException exception) {
            log.error(exception.getMessage(), exception);
        }

        return null;
    }
}
