package com.dimas.stockdataaggregator.configuration;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CsvToolConfig {

    @Bean
    public CsvMapper csvMapper() {
        return CsvMapper.builder().build();
    }

    @Bean
    public CsvSchema csvSchema() {
        return CsvSchema.builder().setUseHeader(true).setColumnSeparator(';').build();
    }
}
