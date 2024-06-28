package com.awaion.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AlibabaSentinelServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AlibabaSentinelServiceApplication.class, args);
    }
}