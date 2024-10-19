package com.dimas.quotationdataaggregatorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
// @EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling
@ConfigurationPropertiesScan
public class QuotationDataAggregatorApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuotationDataAggregatorApplication.class, args);
    }
}
