package com.dimas.moexnotificationservice.model;

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
