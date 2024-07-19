package com.awaken.tool.demo004;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.awaken.tool.demo004.mapper")
@SpringBootApplication
public class Demo004Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo004Application.class, args);
    }

}
