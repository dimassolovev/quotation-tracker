package com.dimas.moexdataservice.model.kafka.currency;

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
public class Current {
    @JsonProperty("secid")
    String secid;
    @JsonProperty("tradedate")
    String tradedate;
    @JsonProperty("tradetime")
    String tradetime;
    @JsonProperty("rate")
    Double rate;
}
