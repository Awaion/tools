# SpringBoot3 + R2DBC

## 代码地址

- Gitee: https://gitee.com/Awaion/tools/tree/master/demo027
- Github: https://github.com/Awaion/tools/tree/master/demo027

## 简介

Reactive Relational Database Connectivity(R2DBC) 项目将响应式编程 API 引入关系数据库

- r2dbc https://r2dbc.io/
- spring-data-r2dbc https://spring.io/projects/spring-data-r2dbc

com.awaion.demo027.Demo027ApplicationTests

## 实现

```text
<dependency>
    <groupId>io.asyncer</groupId>
    <artifactId>r2dbc-mysql</artifactId>
    <version>1.0.5</version>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-r2dbc</artifactId>
</dependency>

spring.r2dbc.password=123456
spring.r2dbc.username=root
spring.r2dbc.url=r2dbc:mysql://localhost:3306/demo027?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&serverZoneId=Asia/Shanghai
spring.r2dbc.name=demo027
```

## 技术说明

#### 后端

| 技术                | 说明                | 官网                                           |
|-------------------|-------------------| ---------------------------------------------- |
| Spring Boot       | Spring Boot       |https://spring.io/projects/spring-boot|
| Spring Data R2DBC | Spring Data R2DBC |https://spring.io/projects/spring-data-r2dbc       |


#### 开发工具

| 工具          | 说明                | 官网                                            |
| ------------- | ------------------- | ----------------------------------------------- |
| IDEA          | 开发IDE             | https://www.jetbrains.com/idea/download         |

#### 开发环境

| 工具     | 版本号  | 下载                                                                                 |
|--------| ------ | ------------------------------------------------------------                         |
| JDK  | 17  | https://www.oracle.com/java/technologies/downloads/#java17 |

#### 启动方式

com.awaion.demo027.Demo027ApplicationTests

## 许可证

[MIT License](https://opensource.org/license/mit)

Copyright (c) 2024-2024 Awaion

