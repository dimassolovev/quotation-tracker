package com.dimas.moexnotificationservice.configuration;

import com.dimas.moexnotificationservice.model.currency.DataFromAggregator;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@RequiredArgsConstructor
public class KafkaConsumerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    private final ObjectMapper objectMapper;

    @Bean
    public Map<String, Object> consumerConfig() {
        Map<String, Object> props = new HashMap<>();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);

        return props;
    }

    @Bean
    public ConsumerFactory<Long, DataFromAggregator> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(
                this.consumerConfig(),
                new LongDeserializer(),
                new ErrorHandlingDeserializer<>(
                        new JsonDeserializer<>(DataFromAggregator.class, objectMapper)
                )
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Long, DataFromAggregator> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Long, DataFromAggregator> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(this.consumerFactory());

        return factory;
    }
}
