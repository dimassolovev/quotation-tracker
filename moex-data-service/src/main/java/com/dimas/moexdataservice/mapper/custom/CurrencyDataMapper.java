package com.dimas.moexdataservice.mapper.custom;

import com.dimas.moexdataservice.model.entity.CurrencyData;
import com.dimas.moexdataservice.model.kafka.DataFromAggregator;
import com.dimas.moexdataservice.model.kafka.currency.MoexCurrencyData;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CurrencyDataMapper implements EntityMapper<CurrencyData, DataFromAggregator<List<MoexCurrencyData>>> {
    @Override
    public CurrencyData toEntity(DataFromAggregator<List<MoexCurrencyData>> dto) {
        List<MoexCurrencyData> currencyDataList = dto.getData();

        for (MoexCurrencyData currencyData : currencyDataList) {
            System.out.println(currencyData);
        }

        return null;
    }
}
