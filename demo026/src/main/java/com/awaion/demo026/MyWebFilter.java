package com.awaion.demo026;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class MyWebFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        // 请求到controller之前,使用该过滤器
        Mono<Void> filter = chain.filter(exchange);

        // 流一旦经过某个操作就会变成新流
        return filter
                .doOnError(err -> System.out.println("目标方法异常以后..."))
                .doFinally(signalType -> System.out.println("目标方法执行以后..."));
    }
}

