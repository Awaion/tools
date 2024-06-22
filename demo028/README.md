# SpringBoot3 + R2DBC + WebFlux + Security

## 代码地址

- Gitee: https://gitee.com/Awaion/tools/tree/master/demo028
- Github: https://github.com/Awaion/tools/tree/master/demo028

## 简介

Reactive Relational Database Connectivity(R2DBC) 项目将响应式编程 API 引入关系数据库

- r2dbc https://r2dbc.io/
- spring-data-r2dbc https://spring.io/projects/spring-data-r2dbc
- spring-webflux https://docs.spring.io/spring-framework/reference/web/webflux.html
- spring-boot-starter-webflux https://docs.spring.io/spring-boot/reference/web/reactive.html
- spring-security https://spring.io/projects/spring-security
- spring-boot-starter-security https://docs.spring.io/spring-boot/reference/web/spring-security.html

http://localhost:8080/hello

http://localhost:8080/world

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
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webflux</artifactId>
</dependency>

spring.r2dbc.password=123456
spring.r2dbc.username=root
spring.r2dbc.url=r2dbc:mysql://localhost:3306/demo028?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&serverZoneId=Asia/Shanghai
spring.r2dbc.name=demo028

@Component
public class MyReactiveUserDetailsService implements ReactiveUserDetailsService {
    @Autowired
    DatabaseClient databaseClient;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        String sql = "SELECT t1.username AS `username`, t1.`password` AS `password`, t3.`value` AS `roleCode`, t5.`value` AS `permissionCode` FROM t_user t1 LEFT JOIN t_user_role t2 ON t2.user_id = t1.id LEFT JOIN t_roles t3 ON t3.id = t2.role_id LEFT JOIN t_role_perm t4 ON t4.role_id = t3.id LEFT JOIN t_perm t5 ON t4.perm_id = t5.id WHERE t1.username = ?";
        Mono<UserDetails> userDetailsMono = databaseClient.sql(sql)
                .bind(0, username)
                .fetch()
                .one()
                .map(map -> {
                    UserDetails details = User.builder()
                            .username(username)
                            .password(map.get("password").toString())
//                            .roles(map.get("roleCode").toString())
                            .authorities(map.get("permissionCode").toString()) // 源代码是新创建权限集合 AuthorityUtils.createAuthorityList
                            .build();
                    return details;
                });
        return userDetailsMono;
    }
}

@EnableR2dbcRepositories
@Configuration
public class MyR2DBCConfiguration {
}

@Configuration
@EnableReactiveMethodSecurity
public class MySecurityConfiguration {
    @Autowired
    ReactiveUserDetailsService appReactiveUserDetailsService;

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange(authorize -> {
            authorize.matchers(PathRequest.toStaticResources()
                    .atCommonLocations()).permitAll();
            authorize.anyExchange().authenticated();
        });
        http.formLogin(formLoginSpec -> {
//            formLoginSpec.loginPage(""); // 指定登录页
        });
        http.csrf(ServerHttpSecurity.CsrfSpec::disable);
        http.authenticationManager(
                new UserDetailsRepositoryReactiveAuthenticationManager(
                        appReactiveUserDetailsService)
        );
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
```

## 技术说明

#### 后端

| 技术                             | 官网                                           |
|--------------------------------| ---------------------------------------------- |
| spring-boot-starter            |https://spring.io/projects/spring-boot|
| spring-boot-starter-data-r2dbc |https://spring.io/projects/spring-data-r2dbc       |
| spring-boot-starter-webflux    | https://docs.spring.io/spring-boot/reference/web/reactive.html |
| spring-boot-starter-security   | https://docs.spring.io/spring-boot/reference/web/spring-security.html |

#### 开发工具

| 工具          | 说明                | 官网                                            |
| ------------- | ------------------- | ----------------------------------------------- |
| IDEA          | 开发IDE             | https://www.jetbrains.com/idea/download         |

#### 开发环境

| 工具     | 版本号  | 下载                                                                                 |
|--------| ------ | ------------------------------------------------------------                         |
| JDK  | 17  | https://www.oracle.com/java/technologies/downloads/#java17 |

#### 启动方式

main

## 许可证

[MIT License](https://opensource.org/license/mit)

Copyright (c) 2024-2024 Awaion

