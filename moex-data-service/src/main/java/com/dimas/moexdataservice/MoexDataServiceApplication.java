package com.dimas.moexdataservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MoexDataServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MoexDataServiceApplication.class, args);
    }
}
