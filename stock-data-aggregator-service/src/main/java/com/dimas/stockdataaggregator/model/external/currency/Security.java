package com.dimas.stockdataaggregator.model.external.currency;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Security {
    @JsonProperty("tradedate") private String tradedate;
    @JsonProperty("tradetime") private String tradetime;
    @JsonProperty("secid") private String secid;
    @JsonProperty("rate") private Double rate;
    @JsonProperty("clearing") private String clearing;
}
