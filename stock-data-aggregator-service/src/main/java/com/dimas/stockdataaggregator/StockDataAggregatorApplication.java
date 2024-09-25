package com.dimas.stockdataaggregator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
// @EnableDiscoveryClient
@EnableFeignClients
@ConfigurationPropertiesScan
@EnableAsync
public class StockDataAggregatorApplication {
	public static void main(String[] args) {
		SpringApplication.run(StockDataAggregatorApplication.class, args);
	}
}
