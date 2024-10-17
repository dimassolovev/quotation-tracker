package com.dimas.moexdataservice.model.kafka.currency;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CurrentData {
    String secid;
    String tradedate;
    String tradetime;
    Double rate;
}
