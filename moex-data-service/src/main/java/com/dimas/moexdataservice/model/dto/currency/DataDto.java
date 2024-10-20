package com.dimas.moexdataservice.model.dto.currency;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Shell object")
public class DataDto<T extends Iterable<CurrencyDataDto>> {
    @Schema(description = "Kind of collection of CurrencyDataDto objects")
    private T data;
}
