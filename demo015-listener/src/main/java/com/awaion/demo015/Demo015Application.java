package com.awaion.demo015;

import com.awaion.demo016.annotation.EnableDemo016Api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDemo016Api // 启用 Demo016Api 功能
@SpringBootApplication
public class Demo015Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo015Application.class, args);
    }

}
