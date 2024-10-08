package com.dimas.stockdataaggregator.kafka.producer;

import com.dimas.stockdataaggregator.constant.property.KafkaConfigurationProperty;
import com.dimas.stockdataaggregator.model.external.DataFromExternalServices;

import com.dimas.stockdataaggregator.model.external.moex.currency.CurrencyData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.kafka.clients.producer.RecordMetadata;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaDataMessagePublisher {
    private final KafkaTemplate<Long, DataFromExternalServices<?>> kafkaTemplate;
    private final KafkaConfigurationProperty kafkaConfigurationProperty;

    public void sendMessage(DataFromExternalServices<?>  dataFromExternalServices) {
        CompletableFuture<SendResult<Long, DataFromExternalServices<?>>> sendResult = this.kafkaTemplate
                .send(
                        this.kafkaConfigurationProperty.getTopic(),
                        dataFromExternalServices
                );

        try {
            SendResult<Long, DataFromExternalServices<?>> result = sendResult.get();
            RecordMetadata recordMetadata = result.getRecordMetadata();
            log.info(
                    String.format(
                            "Sent data to the topic: %s. Partition: %s. OffSet %s. Timestamp: %s",
                            recordMetadata.topic(),
                            recordMetadata.partition(),
                            recordMetadata.offset(),
                            recordMetadata.timestamp()
                    )
            );

        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
        }
    }
}