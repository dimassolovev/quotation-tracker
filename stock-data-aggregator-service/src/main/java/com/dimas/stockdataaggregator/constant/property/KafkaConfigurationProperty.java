package com.dimas.stockdataaggregator.constant.property;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Needed to keep the topic name and messages in it.
 * All parameters are used in src/main/resources/application.yml
 */
@Component
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = "kafka")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KafkaConfigurationProperty {
    Integer retention;
    String topic;
}
