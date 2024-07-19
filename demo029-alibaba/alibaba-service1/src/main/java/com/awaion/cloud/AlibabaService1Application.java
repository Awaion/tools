package com.awaion.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AlibabaService1Application {
    public static void main(String[] args) {
        SpringApplication.run(AlibabaService1Application.class, args);
    }
}