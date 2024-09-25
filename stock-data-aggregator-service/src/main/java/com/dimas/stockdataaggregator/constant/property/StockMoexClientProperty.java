package com.dimas.stockdataaggregator.constant.property;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = "scheduler.stock-moex-client")
public class StockMoexClientProperty {
    private String cronExpression;
    private Integer limit;
    private String metaMode;
    private String language;
    private String sortColumn;
    private String sortOrder;
    private Integer start;
    private Integer numTrades;
}
