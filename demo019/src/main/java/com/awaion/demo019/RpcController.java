package com.awaion.demo019;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
public class RpcController {
    @Autowired
    private Demo019Service demo019Service;

    // http://localhost:8080/rpc/get-api?param=bilibili
    @GetMapping("/rpc/get-api")
    public Mono<String> getApi(@RequestParam("param") String param) {
        return demo019Service.getApi(param);
    }

    // http://localhost:8080/rpc/post-api
    @PostMapping("/rpc/post-api")
    public Mono<String> postApi(@RequestBody Map<String, Object> body) {
        return demo019Service.postApi(body);
    }

    // http://localhost:8080/rpc/get-api-by-web-client?param=bilibili {"hello": "world"}
    @GetMapping("/rpc/get-api-by-web-client")
    public Mono<String> getByWebClient(@RequestParam("param") String param) {
        return demo019Service.getByWebClient(param);
    }
}

