package com.dimas.moexnotificationservice.model.currency;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataFromAggregator  {
    List<CurrencyData> data;
}
