package com.dimas.quotationdataaggregatorservice.publisher;

import com.dimas.quotationdataaggregatorservice.model.external.DataFromExternalServices;

public interface Publisher<T> {
    void publishResponseFromSourcesEvent(DataFromExternalServices<T> dataFromExternalServices, String topicName);
}
