package com.dimas.moexnotificationservice.model.currency;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataFromAggregator <T>  {
    T data;
}
