package com.dimas.moexnotificationservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Current {
    @JsonProperty("secid") private String secid;
    @JsonProperty("tradedate") private String tradedate;
    @JsonProperty("tradetime") private String tradetime;
    @JsonProperty("rate") private Double rate;
}
