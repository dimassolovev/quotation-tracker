package com.dimas.stockdataaggregator.constant.property;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = "scheduler.stock-moex-client")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StockMoexClientProperty {
    String cronExpression;
    Integer limit;
    String metaMode;
    String language;
    String sortColumn;
    String sortOrder;
    Integer start;
    Integer numTrades;
}
