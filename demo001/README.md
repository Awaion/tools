# `SpringBoot`项目在外部`Tomcat`服务器上运行

## 代码地址

- Gitee: https://gitee.com/Awaion/tools/tree/master/demo001
- Github: https://github.com/Awaion/tools/tree/master/demo001 

## 项目演示

本地服务启动访问地址：http://localhost

![首页](src/main/resources/document/20240406164219.jpg)

## 技术点

1. pom.xml

```
<packaging>war</packaging>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <exclusions>
        <!-- 移除内嵌tomcat -->
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>
    </exclusions>
</dependency>

<!-- 给idea运行main时使用,打包时不使用 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-tomcat</artifactId>
    <scope>provided</scope>
</dependency>
```

2. Demo001Application.java 继承SpringBootServletInitializer类,并重写configure方法

```code
@SpringBootApplication
public class Demo001Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 外部tomcat启动需要调用初始化资源
        return builder.sources(Demo001Application.class);
    }

}
```

3. 打包项目

``` code
mvn clean package -Dmaven.test.skip=true
```

## 技术说明

#### 后端

| 技术                 | 说明                | 官网                                           |
| -------------------- | ------------------- | ---------------------------------------------- |
| SpringBoot           | Web应用开发框架      | https://spring.io/projects/spring-boot         |

#### 前端

| 技术         | 说明                   | 官网                                   |
| ----------  | ---------------------  | -------------------------------------- |
| Bootstrap3  | 前端框架               | https://www.bootcss.com/               |
| jQuery1     | 路由框架               | https://blog.jquery.com/               |

#### 开发工具

| 工具          | 说明                | 官网                                            |
| ------------- | ------------------- | ----------------------------------------------- |
| IDEA          | 开发IDE             | https://www.jetbrains.com/idea/download         |

#### 开发环境

| 工具          | 版本号  | 下载                                                                                 |
| ------------- | ------ | ------------------------------------------------------------                         |
| JDK           | 1.8    | https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html |

#### 启动方式

1. 使用idea打开此代码并打包
2. 将war包丢入tomcat/webapps中
3. 启动tomcat,访问 http://localhost:8080/demo001-0.0.1-SNAPSHOT

## 许可证
[MIT License](https://opensource.org/license/mit)

Copyright (c) 2024-2024 awaion

