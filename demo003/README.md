# springboot相关知识点

## 友情提示
> 1. **代码地址**：[码云](https://gitee.com/explore) 。

## 前言
`awaken`项目致力于基于SpringBoot基础上,集成各种技术框架,用于理解框架集成原理。

## 项目文档
文档地址：[https://gitee.com/explore](https://gitee.com/explore)

## 当前项目介绍
> SpringBoot 集成 spring-data-jpa

> Spring Data JPA 是 Spring 框架下的一个模块,是基于 JPA 规范的上层封装,旨在简化 JPA 的使用.
  
> Spring Data JPA 提供了一些常用的接口,如 JpaRepository,JpaSpecificationExecutor 等,这些接口包含了很多常用的 CRUD 操作方
法,可直接继承使用.同时 Spring Data JPA 还提供了基于方法命名规范的查询方式,可以根据方法名自动生成相应的 SQL 语句,并执行查
询操作.这种方式可以大大减少编写 SQL 语句的工作量.
  
> 除了基础的 CRUD 操作外,Spring Data JPA 还提供了一些高级功能,如分页/排序/动态查询等.同时它也支持多种数据库,如 MySQL/
PostgreSQL/Oracle 等. 总结如下:  
>> * Spring Data JPA 是 Spring Data 项目家族中的一员,它为基于Spring框架应用程序提供了更加便捷和强大的数据操作方式.
>> * Spring Data JPA 支持多种数据存储技术,包括关系型数据库和非关系型数据库.
>> * Spring Data JPA 提供了简单/一致且易于使用的API来访问和操作数据存储,其中包括基本的CRUD操作/自定义查询方法/动态查询等功能.
>> * Spring Data JPA 也支持QueryDSL/Jinq/Kotlin Query等其他查询框架

> Spring Data JPA 官方文档: https://spring.io/projects/spring-data-jpa

### 项目演示
本地服务启动访问地址：http://localhost/doc.html
![首页](./document/20240409145010.png)

### 技术点
1. pom.xml 新增 Spring Data JPA 依赖
``` code
<!-- spring-data-jpa,内置hikari -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

2. application.yaml新增数据库及相关配置
```code
# 数据库连接基础配置
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/jpa?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai
    username: root
    password: 123456
    driver-class-name: com.mysql.cj.jdbc.Driver
    hikari:
      idle-timeout: 30000
      connection-timeout: 10000
      maximum-pool-size: 15
      minimum-idle: 5
      auto-commit: true
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    database-platform: org.hibernate.dialect.MySQL55Dialect
```

3. 持久层继承接口
```code
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
```

4. 实体类使用注解
``` code
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;
```

### 技术选型
#### 后端技术
| 技术                 | 说明                | 官网                                           |
| -------------------- | ------------------- | ---------------------------------------------- |
| SpringBoot           | Web应用开发框架      | https://spring.io/projects/spring-boot         |

#### 前端技术
| 技术         | 说明                   | 官网                                   |
| ----------  | ---------------------  | -------------------------------------- |
| Bootstrap4  | 前端框架               | https://www.bootcss.com/               |
| jQuery3     | 路由框架               | https://blog.jquery.com/               |

#### 架构图
##### 系统架构图
loading...

## 环境搭建
### 开发工具
| 工具          | 说明                | 官网                                            |
| ------------- | ------------------- | ----------------------------------------------- |
| IDEA          | 开发IDE             | https://www.jetbrains.com/idea/download         |

### 开发环境
| 工具          | 版本号  | 下载                                                                                 |
| ------------- | ------ | ------------------------------------------------------------                         |
| JDK           | 1.8    | https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html |


### 搭建步骤
> Windows环境启动
- main方法启动

## 许可证
[Apache License 2.0](https://github.com/macrozheng/mall/blob/master/LICENSE)

Copyright (c) 2024-2024 Awaion

