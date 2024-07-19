package com.awaion.demo026;

import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Controller
public class HelloController {

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

}

