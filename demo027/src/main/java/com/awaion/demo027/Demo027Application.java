package com.awaion.demo027;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 2dbcAutoConfiguration: 连接工厂,连接池
 * R2dbcDataAutoConfiguration: R2dbcEntityTemplate,R2dbcCustomConversions
 * R2dbcRepositoriesAutoConfiguration: 开启Spring Data声明式接口方式的CRUD
 * Spring Data 提供了基础的CRUD接口
 * R2dbcTransactionManagerAutoConfiguration: 事务管理
 */
@SpringBootApplication
public class Demo027Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo027Application.class, args);
    }

}
