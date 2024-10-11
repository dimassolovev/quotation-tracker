package com.dimas.quotationdataaggregatorservice.model.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A simple container class that can store data from various sources.
 * @param <T> generic is regular pojo class.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataFromExternalServices<T> {
    private T data;
}
