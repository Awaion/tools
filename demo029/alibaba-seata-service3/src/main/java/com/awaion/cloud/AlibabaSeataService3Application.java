package com.awaion.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.awaion.cloud.mapper") //import tk.mybatis.spring.annotation.MapperScan;
@SpringBootApplication
public class AlibabaSeataService3Application {
    public static void main(String[] args) {
        SpringApplication.run(AlibabaSeataService3Application.class, args);
    }
}