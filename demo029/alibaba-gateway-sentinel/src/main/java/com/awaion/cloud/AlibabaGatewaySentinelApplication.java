package com.awaion.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AlibabaGatewaySentinelApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(AlibabaGatewaySentinelApplication.class,args);
    }
}