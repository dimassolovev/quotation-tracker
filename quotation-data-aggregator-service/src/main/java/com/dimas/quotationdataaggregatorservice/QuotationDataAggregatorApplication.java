package com.dimas.quotationdataaggregatorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
// @EnableDiscoveryClient
@EnableFeignClients
@ConfigurationPropertiesScan
public class QuotationDataAggregatorApplication {
	public static void main(String[] args) {
		SpringApplication.run(QuotationDataAggregatorApplication.class, args);
	}
}
