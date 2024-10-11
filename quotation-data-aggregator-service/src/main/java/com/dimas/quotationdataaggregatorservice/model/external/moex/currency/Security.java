package com.dimas.quotationdataaggregatorservice.model.external.moex.currency;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Security {
    @JsonProperty("tradedate")
    String tradedate;
    @JsonProperty("tradetime")
    String tradetime;
    @JsonProperty("secid")
    String secid;
    @JsonProperty("rate")
    Double rate;
    @JsonProperty("clearing")
    String clearing;
}
