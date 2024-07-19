# Spring Boot 3 + Spring Web + MyBatis

## 代码地址

- Gitee: https://gitee.com/Awaion/tools/tree/master/demo014
- Github: https://github.com/Awaion/tools/tree/master/demo014

## 简介

如题

## 演示

http://localhost:8080/get

{"id":1,"loginName":"登录名01","password":"密码01"}

### 集成步骤

```
// pom.xml 新增依赖
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>3.0.3</version>
</dependency>
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>

// application.properties 新增配置
# 数据源配置
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/demo014
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.type=com.zaxxer.hikari.HikariDataSource
# 指定mapper映射文件位置
mybatis.mapper-locations=classpath:/mapper/*.xml
# 驼峰映射
mybatis.configuration.map-underscore-to-camel-case=true

// *Mapper
public interface LoginInfoMapper {
    LoginInfo getLoginInfoById(@Param("id") Long id);
}

// *Mapper.xml
<select id="getLoginInfoById" resultType="com.awaion.demo014.domain.LoginInfo">
    select
        *
    from login_info
    where
        id = #{id}
</select>

// *Controller
@RestController
public class Demo014Controller {
    @GetMapping("/get")
    public LoginInfo getById() {
        return mapper.getLoginInfoById(1L);
    }
}
```

### 技术说明

#### 后端

| 技术           | 说明                | 官网                                           |
|--------------| ------------------- | ---------------------------------------------- |
| SpringBoot 3 | SpringBoot     | https://spring.io/projects/spring-boot         |
| SpringWeb    | Web应用开发框架     | https://spring.io/projects/spring-boot         |
| MyBatis 3    | 持久层框架          | https://mybatis.org/mybatis-3/                           |

#### 开发工具

| 工具          | 说明                | 官网                                            |
| ------------- | ------------------- | ----------------------------------------------- |
| IDEA          | 开发IDE             | https://www.jetbrains.com/idea/download         |

#### 开发环境

| 工具     | 版本号  | 下载                                                                                 |
|--------| ------ | ------------------------------------------------------------                         |
| JDK  | 17  | https://www.oracle.com/java/technologies/downloads/#java17 |

#### 启动方式

main方法启动

## 许可证

[MIT License](https://opensource.org/license/mit)

Copyright (c) 2024-2024 Awaion

