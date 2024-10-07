package com.dimas.moexnotificationservice.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class MoexDataKafkaConsumer {
    @RetryableTopic(attempts = "4") // 3 topic N-1
    @KafkaListener(
            topics = "quote.moex-data",
            groupId = "dimas.group"
    )
    public void onMessage(
            String message
    ) {
        System.out.println(message);
    }
}
