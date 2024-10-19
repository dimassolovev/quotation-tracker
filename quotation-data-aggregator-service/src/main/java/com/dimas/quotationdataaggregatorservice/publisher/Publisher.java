package com.dimas.quotationdataaggregatorservice.publisher;

public interface Publisher<T> {
    void publishResponseFromSourcesEvent(T dataFromExternalServices, String topicName);
}
