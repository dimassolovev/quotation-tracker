package com.dimas.moexdataservice.model.kafka.currency;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoexCurrencyDataFromAggregator {
    List<MoexCurrencyData> data;
}
