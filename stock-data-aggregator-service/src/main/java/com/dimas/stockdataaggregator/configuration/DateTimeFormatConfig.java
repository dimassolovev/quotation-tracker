package com.dimas.stockdataaggregator.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

@Configuration
public class DateTimeFormatConfig {
    @Bean
    public DateTimeFormatter dateTimeFormat() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }
}
