package com.dimas.quotationdataaggregatorservice.kafka.producer;

import com.dimas.quotationdataaggregatorservice.model.external.DataFromExternalServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducerImplementation implements KafkaProducer<DataFromExternalServices<?>> {
    private final KafkaTemplate<String, DataFromExternalServices<?>> kafkaTemplate;
    private final DateTimeFormatter dateTimeFormatter;

    public void sendMessage(DataFromExternalServices<?> dataFromExternalServices, String topicName) {
        CompletableFuture<SendResult<String, DataFromExternalServices<?>>> sendResult = this.kafkaTemplate
                .send(
                        topicName,
                        LocalDateTime.now().format(dateTimeFormatter),
                        dataFromExternalServices
                );

        sendResult.whenComplete(
                (result, exception) -> {
                    if (exception != null) {
                        log.error(exception.getMessage(), exception);
                        return;
                    }
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
                }
        );
    }
}
