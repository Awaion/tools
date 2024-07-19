# SpringBoot3 + WebFlux

## 代码地址

- Gitee: https://gitee.com/Awaion/tools/tree/master/demo026
- Github: https://github.com/Awaion/tools/tree/master/demo026

## 简介

- Reactive Web Applications https://docs.spring.io/spring-boot/reference/web/reactive.html
- Spring WebFlux https://docs.spring.io/spring-framework/reference/web/webflux.html

http://localhost:8080/hello

http://localhost:8080/render

http://localhost:8080/mono

http://localhost:8080/flux

http://localhost:8080/sse

## 实现

com.awaion.demo026.Demo026Application.main

```text
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webflux</artifactId>
</dependency>

@GetMapping("/hello")
@ResponseBody
public String hello(@RequestParam(value = "param", required = false, defaultValue = "defaultValue") String param) {
    // WebFlux 向下兼容原来 SpringMVC 的大多数注解和API
    // 可接受的请求参数
    // ServerWebExchange exchange, exchange.getRequest();exchange.getResponse();
    // WebSession webSession, webSession.getAttribute("");webSession.getAttributes().put("", "");
    // HttpMethod method, method.name();
    // HttpEntity<String> entity,
    // @RequestBody String s,
    // FilePart file, file.transferTo() // 零拷贝技术

    return "Hello World param=" + param;
}

@GetMapping("/render")
public Rendering render() {
//        Rendering.redirectTo("/"); // 重定向到当前项目根路径
    return Rendering.redirectTo("https://www.baidu.com").build();
}

@GetMapping("/mono")
@ResponseBody
public Mono<String> mono() {
    // 返回单个数据Mono
    return Mono.just(0)
            .map(i -> 10 / i)
            .map(i -> "mono-" + i);
}

@GetMapping("/flux")
@ResponseBody
public Flux<String> flux() {
    // 返回多个数据Flux
    return Flux.just("flux1", "flux2");
}

@GetMapping(value = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
@ResponseBody
public Flux<ServerSentEvent<String>> sse() {
    // Flux + SSE(Server Send Event) 服务端事件推送
    return Flux.range(1, 10)
            .map(i -> ServerSentEvent.builder("sse-" + i)
                    .id(i + "")
                    .comment("comment-" + i)
                    .event("event")
                    .build())
            .delayElements(Duration.ofMillis(500));
}
```

## 技术说明

#### 后端

| 技术           | 说明                | 官网                                           |
|--------------| ------------------- | ---------------------------------------------- |
|Spring Boot   |Spring Boot         |https://spring.io/projects/spring-boot|
|Spring Web Flow  |Spring Web Flow|https://spring.io/projects/spring-webflow       |


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

