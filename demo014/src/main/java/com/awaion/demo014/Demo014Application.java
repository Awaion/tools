package com.awaion.demo014;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.awaion.demo014.mapper")
@SpringBootApplication
public class Demo014Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo014Application.class, args);
    }

}
