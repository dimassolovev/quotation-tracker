package com.dimas.quotationdataaggregatorservice.configuration;

import com.dimas.quotationdataaggregatorservice.constant.property.KafkaConfigurationProperty;
import com.dimas.quotationdataaggregatorservice.model.external.DataFromExternalServices;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.TopicConfig;
import org.apache.kafka.common.serialization.LongSerializer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    private final ObjectMapper objectMapper;

    @Bean
    public Map<String, Object> producerConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrapServers);

        return props;
    }

    @Bean
    public ProducerFactory<Long, DataFromExternalServices<?>> producerFactory() {


        return new DefaultKafkaProducerFactory<>(
                this.producerConfig(),
                new LongSerializer(),
                new JsonSerializer<>(objectMapper)
        );
    }

    @Bean
    public KafkaTemplate<Long, DataFromExternalServices<?>> kafkaTemplate() {
        return new KafkaTemplate<>(this.producerFactory());
    }

    @Bean
    public NewTopic kafkaTopics(KafkaConfigurationProperty kafkaConfigurationProperty) {
        return TopicBuilder
                .name(kafkaConfigurationProperty.getTopic())
                .partitions(3)
                .replicas(2)
                .config(
                        TopicConfig.RETENTION_MS_CONFIG,
                        String.valueOf(Duration.ofDays(
                                        kafkaConfigurationProperty
                                                .getRetention()
                                        )
                                .toMillis()
                        )
                )
                .build();
    }
}
