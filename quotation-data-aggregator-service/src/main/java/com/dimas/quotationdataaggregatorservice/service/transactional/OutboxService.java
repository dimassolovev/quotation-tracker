package com.dimas.quotationdataaggregatorservice.service.transactional;

import com.dimas.quotationdataaggregatorservice.model.entity.Outbox;
import com.dimas.quotationdataaggregatorservice.model.external.moex.currency.CurrencyData;
import com.dimas.quotationdataaggregatorservice.model.type.StatusType;

import java.time.LocalDateTime;
import java.util.List;

public interface OutboxService {
    void saveAll(String topic, List<CurrencyData> currencyDataList);

    List<Outbox> getOutboxesByTimestamp(LocalDateTime timestamp);

    void update(Outbox outbox, StatusType statusType);
}
