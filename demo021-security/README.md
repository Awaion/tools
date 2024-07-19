# SpringBoot 3 + Spring Security

## 代码地址

- Gitee: https://gitee.com/Awaion/tools/tree/master/demo021
- Github: https://github.com/Awaion/tools/tree/master/demo021

## 简介

如题

## 演示

http://localhost:8080/

## 实现

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>

@Bean
SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    // 请求授权
    http.authorizeHttpRequests(registry -> {
        registry.requestMatchers("/").permitAll() // 首页所有人都允许
                .anyRequest().authenticated(); // 剩下的任意请求都需要认证
    });

    // 表单登录功能,开启默认表单登录功能, Spring Security 提供默认登录页
    http.formLogin(formLogin -> {
        formLogin.loginPage("/login").permitAll(); //自定义登录页位置，并且所有人都能访问
    });

    return http.build();
}

@Bean
UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
    UserDetails zhangsan = User.withUsername("zhangsan")
            .password(passwordEncoder.encode("123456"))
            .roles("admin", "hr")
            .authorities("file_read", "file_write")
            .build();

    UserDetails lisi = User.withUsername("lisi")
            .password(passwordEncoder.encode("123456"))
            .roles("hr")
            .authorities("file_read")
            .build();

    UserDetails wangwu = User.withUsername("wangwu")
            .password(passwordEncoder.encode("123456"))
            .roles("admin")
            .authorities("file_write", "world_exec")
            .build();

    // 默认内存中保存所有用户信息
    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager(zhangsan, lisi, wangwu);
    return manager;
}

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
```

## 技术说明

#### 后端

| 技术           | 说明                | 官网                                           |
|--------------| ------------------- | ---------------------------------------------- |
| SpringBoot 3 | SpringBoot     | https://spring.io/projects/spring-boot         |
| Spring Security | Spring Security     | https://spring.io/projects/spring-security         |

#### 开发工具

| 工具          | 说明                | 官网                                            |
| ------------- | ------------------- | ----------------------------------------------- |
| IDEA          | 开发IDE             | https://www.jetbrains.com/idea/download         |

#### 开发环境

| 工具     | 版本号  | 下载                                                                                 |
|--------| ------ | ------------------------------------------------------------                         |
| JDK  | 17  | https://www.oracle.com/java/technologies/downloads/#java17 |

#### 启动方式

http://localhost:8080/

## 许可证

[MIT License](https://opensource.org/license/mit)

Copyright (c) 2024-2024 Awaion

