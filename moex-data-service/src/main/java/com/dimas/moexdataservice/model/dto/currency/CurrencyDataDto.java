package com.dimas.moexdataservice.model.dto.currency;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Currency data from MOEX model")
public class CurrencyDataDto {
    @Schema(description = "Tradedate", example = "2024-10-14")
    String tradedate;
    @Schema(description = "Tradetime", example = "13:45:00")
    String tradetime;
    @Schema(description = "Exchange rate", example = "96.0686")
    Double rate;
    @Schema(description = "Various types of settlements or clearing related to financial instruments or foreign exchange transactions", example = "pk")
    String clearing;
    @Schema(description = "Pair code for two currencies", example = "USD/RUB")
    String secid;
}
