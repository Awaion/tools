package com.awaion.demo019;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

@HttpExchange(url = "http://localhost:8080")
public interface RpcDemo019Interface {
    @GetExchange(url = "/get-api", accept = "application/json")
    Mono<String> getApi(@RequestParam("param") String param);

    @PostExchange(url = "/post-api", accept = "application/json")
    Mono<String> postApi(@RequestBody Map<String, Object> body);
}
