package com.dimas.quotationdataaggregatorservice.scheduler;

import com.dimas.quotationdataaggregatorservice.kafka.producer.KafkaProducer;
import com.dimas.quotationdataaggregatorservice.mapper.CurrencyDataMapper;
import com.dimas.quotationdataaggregatorservice.model.entity.Outbox;
import com.dimas.quotationdataaggregatorservice.model.external.DataFromExternalServices;
import com.dimas.quotationdataaggregatorservice.model.type.StatusType;
import com.dimas.quotationdataaggregatorservice.service.transactional.OutboxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeliveryCurrencyDataScheduler implements Scheduler {
    private final KafkaProducer<String, DataFromExternalServices<?>> kafkaProducer;
    private final OutboxService outboxService;
    private final CurrencyDataMapper currencyDataMapper;

    @Override
    @Scheduled(cron = "${scheduler.currency-moex-client.outbox-cron-expression}")
    public void poll() {
        List<Outbox> outboxes = outboxService.getOutboxesByTimestamp(LocalDateTime.now());

        Map<CompletableFuture<SendResult<String, DataFromExternalServices<?>>>, Outbox> tasks = outboxes.stream()
                .collect(Collectors.toMap(
                        outbox -> this.kafkaProducer
                                .sendMessage(new DataFromExternalServices<>(this.currencyDataMapper.toEntity(outbox.getPayload())), outbox.getTopic()),
                        outbox -> outbox
                ));

        CompletableFuture.allOf(tasks.keySet().toArray(new CompletableFuture[0]))
                .thenRun(() -> {
                    tasks.forEach((task, outbox) -> task.whenComplete((result, exception) -> {
                        if (exception != null) {
                            this.outboxService.update(outbox, StatusType.FAILED);
                            log.error(String.format("Outbox failed: %s", outbox.getId()));
                        } else {
                            this.outboxService.update(outbox, StatusType.COMPLETED);
                            log.info(String.format("Outbox completed: %s", outbox.getId()));
                        }
                    }));
                })
                .exceptionally(exception -> {
                    log.error(exception.getMessage(), exception);
                    return null;
                });
    }
}
