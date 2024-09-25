package com.dimas.stockdataaggregator.model.external.currency;

import com.dimas.stockdataaggregator.model.external.MoexData;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyData implements MoexData {
    @JsonProperty("securities") private List<Security> securities;
    @JsonProperty("current") private Current current;
}
