package com.dimas.quotationdataaggregatorservice.service.transactional;

import com.dimas.quotationdataaggregatorservice.mapper.CurrencyDataMapper;
import com.dimas.quotationdataaggregatorservice.model.entity.Outbox;
import com.dimas.quotationdataaggregatorservice.model.external.moex.currency.CurrencyData;
import com.dimas.quotationdataaggregatorservice.model.type.StatusType;
import com.dimas.quotationdataaggregatorservice.repository.OutboxRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class OutboxServiceImplementation implements OutboxService {
    private final OutboxRepository outboxRepository;
    private final CurrencyDataMapper currencyDataMapper;

    @Override
    @Transactional
    public void saveAll(String topic, List<CurrencyData> currencyDataList) {
        var outboxList = currencyDataList
                .stream()
                .map(
                        this.currencyDataMapper::toDto
                )
                .filter(Objects::nonNull)
                .map(el ->
                        new Outbox(
                                UUID.randomUUID(),
                                LocalDateTime.now(),
                                StatusType.REQUESTED,
                                topic,
                                el
                        ))
                .toList();

        this.outboxRepository.saveAll(outboxList);
    }

    @Override
    @Transactional
    public void update(Outbox outbox, StatusType statusType) {
        outbox.setStatus(statusType);
        this.outboxRepository.save(outbox);
    }

    @Override
    public List<Outbox> getOutboxesByTimestamp(LocalDateTime timestamp) {
        return this.outboxRepository.findByTimestamp(timestamp);
    }
}
