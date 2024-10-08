package com.dimas.stockdataaggregator.constant.property;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = "scheduler.currency-moex-client")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CurrencyMoexClientProperty {
    Integer limit;
    List<String> securities;
    String metaMode;
    String choice;
    String stringOrder;
}
