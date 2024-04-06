# springboot相关知识点

## 友情提示
> 1. **代码地址**：[码云](https://gitee.com/explore) 。

## 前言
`awaken`项目致力于基于SpringBoot基础上,集成各种技术框架,用于理解框架集成原理。

## 项目文档
文档地址：[https://gitee.com/explore](https://gitee.com/explore)

## 当前项目介绍
如何将项目改造成可使用外部Tomcat服务器运行

### 项目演示
本地服务启动访问地址：http://localhost
![首页](./document/20240406164219.jpg)

### 技术点
1. pom.xml 移除内嵌tomcat,同时新增tomcat依赖,确保idea运行时存在tomcat依赖,打包时不存在tomcat依赖
``` code
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

<!-- 给idea运行时使用,打包时不使用 -->
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

### 技术选型
#### 后端技术
| 技术                 | 说明                | 官网                                           |
| -------------------- | ------------------- | ---------------------------------------------- |
| SpringBoot           | Web应用开发框架      | https://spring.io/projects/spring-boot         |

#### 前端技术
| 技术         | 说明                   | 官网                                   |
| ----------  | ---------------------  | -------------------------------------- |
| Bootstrap3  | 前端框架               | https://vuejs.org/                     |
| jQuery1     | 路由框架               | https://router.vuejs.org/              |

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
- 使用idea打开此代码并打包
- 将war包丢入tomcat/webapps中
- 启动tomcat,并访问 http://localhost:8080/demo001-0.0.1-SNAPSHOT

## 许可证
[Apache License 2.0](https://github.com/macrozheng/mall/blob/master/LICENSE)

Copyright (c) 2024-2024 awaion

