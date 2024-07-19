package com.awaion.demo019;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class Demo019Service {
    @Autowired
    private RpcDemo019Interface rpcDemo019Interface;

    public Mono<String> getApi(String param) {
        return rpcDemo019Interface.getApi(param);
    }

    public Mono<String> postApi(Map<String, Object> body) {
        return rpcDemo019Interface.postApi(body);
    }

    public Mono<String> getByWebClient(String param) {
        WebClient client = WebClient.create();
        Map<String, String> params = new HashMap<>();
        params.put("param", param);
        return client.get()
                .uri("http://localhost:8080/get-api?param={param}", params)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "qazwsx")
                .retrieve()
                .bodyToMono(String.class);
    }
}

