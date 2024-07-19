# SpringBoot 3 + Redis

## 代码地址

- Gitee: https://gitee.com/Awaion/tools/tree/master/demo017
- Github: https://github.com/Awaion/tools/tree/master/demo017

## 简介

如题

## 演示

com.awaion.demo017.Demo017ApplicationTests.redisTest

## 实现

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>

/**
 * 替换默认的 RedisTemplate 自定义序列化
 */
@Bean
public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
    RedisTemplate<Object, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(redisConnectionFactory);
    // json 序列化
    template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
    return template;
}

@Autowired
StringRedisTemplate redisTemplate;

@Test
void redisTest(){
    redisTemplate.opsForValue().set("a","1234");
    Assertions.assertEquals("1234",redisTemplate.opsForValue().get("a"));
}
```

## 技术说明

#### 后端

| 技术                | 说明                | 官网                                           |
|-------------------| ------------------- | ---------------------------------------------- |
| SpringBoot 3      | SpringBoot     | https://spring.io/projects/spring-boot         |
| Spring Data Redis | Redis     | https://spring.io/projects/spring-data-redis         |

#### 开发工具

| 工具          | 说明                | 官网                                            |
| ------------- | ------------------- | ----------------------------------------------- |
| IDEA          | 开发IDE             | https://www.jetbrains.com/idea/download         |

#### 开发环境

| 工具     | 版本号  | 下载                                                                                 |
|--------| ------ | ------------------------------------------------------------                         |
| JDK  | 17  | https://www.oracle.com/java/technologies/downloads/#java17 |

#### 启动方式

com.awaion.demo017.Demo017ApplicationTests.redisTest

## 许可证

[MIT License](https://opensource.org/license/mit)

Copyright (c) 2024-2024 Awaion

