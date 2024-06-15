package com.awaion.demo020;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka // 启动 kafka
@SpringBootApplication
public class Demo020Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo020Application.class, args);
    }

}
