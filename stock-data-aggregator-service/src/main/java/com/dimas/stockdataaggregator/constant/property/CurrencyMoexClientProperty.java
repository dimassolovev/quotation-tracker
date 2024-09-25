package com.dimas.stockdataaggregator.constant.property;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = "scheduler.currency-moex-client")
public class CurrencyMoexClientProperty {
    private Integer limit;
    private List<String> securities;
    private String metaMode;
    private String choice;
    private String stringOrder;
}
