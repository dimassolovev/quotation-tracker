package com.dimas.moexdataservice.model.dto.currency;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyDataDto {
    String tradedate;
    String tradetime;
    Double rate;
    String clearing;
    String secid;
}
