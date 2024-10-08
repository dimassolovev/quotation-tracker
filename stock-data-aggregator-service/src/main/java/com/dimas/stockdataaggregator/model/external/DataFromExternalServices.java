package com.dimas.stockdataaggregator.model.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataFromExternalServices<T> {
    private T data;
}
