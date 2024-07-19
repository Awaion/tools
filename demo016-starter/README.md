# Spring Boot 3 自定义 starter

## 代码地址

- Gitee: https://gitee.com/Awaion/tools/tree/master/demo016
- Github: https://github.com/Awaion/tools/tree/master/demo016

## 简介

如题

## 演示

通过 @EnableDemo016Api 注解启用 http://localhost:8080/demo016/getInfo

自动配置 http://localhost:8080/demo016/auto/getInfo

## 实现

```
/**
 * 读取配置文件 demo016.*
 */
@ConfigurationProperties(prefix = "demo016")
@Component
@Data
public class Demo016Properties {
    private String loginName = "admin";
    private String password = "1qaz@wsx";
}

// 配置文件
demo016.login-name=zhangsan
demo016.password=lisi

// 业务代码
@Slf4j
@Service
public class Demo016Service {
    @Autowired
    private Demo016Properties properties;

    public String getInfo() {
        return "loginName:" + properties.getLoginName() + ",password:" + properties.getPassword();
    }
}

// 业务代码
@Slf4j
@RestController
public class Demo016Controller {
    @Autowired
    private Demo016Service service;

    /**
     * http://localhost:8080/demo016/getInfo
     * http://localhost:8081/demo016/getInfo
     */
    @GetMapping("/demo016/getInfo")
    public String getInfo() {
        return service.getInfo();
    }
}

// 业务代码
@Slf4j
@RestController
public class Demo016AutoController {
    @Autowired
    private Demo016Service service;

    /**
     * http://localhost:8080/demo016/auto/getInfo
     * http://localhost:8081/demo016/auto/getInfo
     */
    @GetMapping("/demo016/auto/getInfo")
    public String getInfo() {
        return service.getInfo()+service.getInfo();
    }
}

/**
 * 配置组件
 */
@Import({Demo016Properties.class}) // 导入别的配置组件
@Configuration
public class Demo016Configuration {
    @Bean
    public Demo016Service demo016Service() {
        return new Demo016Service();
    }

    @Bean
    public Demo016Controller demo016Controller() {
        return new Demo016Controller();
    }
}

/**
 * 自动配置组件
 * 自动加载路径 META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
 * 自动加载路径2 META-INF/spring.factories
 */
@Import({Demo016Properties.class}) // 导入别的配置组件
@Configuration
public class Demo016AutoConfiguration {
    @ConditionalOnMissingBean // 条件加载
    @Bean
    public Demo016Service demo016Service() {
        return new Demo016Service();
    }

    @Bean
    public Demo016AutoController demo016AutoController() {
        return new Demo016AutoController();
    }
}

/**
 * 启用注解,启用配置 Demo016Configuration
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({Demo016Configuration.class})
public @interface EnableDemo016Api {
}

<!-- 新增自定义启动组件 -->
<dependency>
    <groupId>com.awaion</groupId>
    <artifactId>demo016-spring-boot-starter</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>

@EnableDemo016Api // 启用 Demo016Api 功能
```

## 技术说明

#### 后端

| 技术           | 说明                | 官网                                           |
|--------------| ------------------- | ---------------------------------------------- |
| SpringBoot 3 | SpringBoot     | https://spring.io/projects/spring-boot         |
| SpringWeb    | Web应用开发框架     | https://spring.io/projects/spring-boot         |

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

