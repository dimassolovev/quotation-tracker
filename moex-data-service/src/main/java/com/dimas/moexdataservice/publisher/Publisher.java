package com.dimas.moexdataservice.publisher;

import com.dimas.moexdataservice.model.kafka.currency.DataFromAggregator;

public interface Publisher<T> {
    void publishResponseFromSourcesEvent(DataFromAggregator<T> dataFromExternalServices);
}
