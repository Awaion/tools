package com.awaion.cloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@RestController
public class Resilience4jController {
    @GetMapping(value = "/resilience4j/circuitbreaker/{id}")
    public String myCircuit(@PathVariable("id") Integer id) throws InterruptedException {
        // Resilience4j CircuitBreaker 断路器 测试接口
        // http://localhost:10001/resilience4j/circuitbreaker/0 抛异常
        // http://localhost:10001/resilience4j/circuitbreaker/9999 慢响应
        // http://localhost:10001/resilience4j/circuitbreaker/6 正常
        if (id == 0) throw new RuntimeException("参数异常");
        if (id == 9999) TimeUnit.SECONDS.sleep(5);
        return "Resilience4j CircuitBreaker:" + LocalDateTime.now();
    }

    @GetMapping(value = "/resilience4j/bulkhead/{id}")
    public String myBulkhead(@PathVariable("id") Integer id) throws InterruptedException {
        // Resilience4j Bulkhead 舱壁隔离 基于semaphore(信号量)实现
        // http://localhost:10001/resilience4j/bulkhead/0 抛异常
        // http://localhost:10001/resilience4j/bulkhead/9999 慢响应
        // http://localhost:10001/resilience4j/bulkhead/6 正常
        if (id == 0) throw new RuntimeException("参数异常");
        if (id == 9999) TimeUnit.SECONDS.sleep(5);
        return "Resilience4j Bulkhead:" + LocalDateTime.now();
    }

    @GetMapping(value = "/resilience4j/ratelimiter/{id}")
    public String myRatelimit(@PathVariable("id") Integer id) {
        // Resilience4j RateLimiter 限流
        // http://localhost:10001/resilience4j/ratelimiter/6 正常
        return "Resilience4j RateLimiter:" + id + "," + LocalDateTime.now();
    }
}
