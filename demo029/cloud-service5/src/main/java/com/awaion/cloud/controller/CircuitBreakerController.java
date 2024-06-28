package com.awaion.cloud.controller;

import com.awaion.cloud.apis.CloudService1Api;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
public class CircuitBreakerController {
    @Resource
    private CloudService1Api api;
    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/feign/resilience4j/circuitbreaker/{id}")
    @CircuitBreaker(name = "cloud-service1", fallbackMethod = "myCircuitFallback")
    public String myCircuitBreaker(@PathVariable("id") Integer id) throws InterruptedException {
        // Resilience4j CircuitBreaker 断路器 测试接口
        // http://localhost:10005/feign/resilience4j/circuitbreaker/0 抛异常
        // http://localhost:10005/feign/resilience4j/circuitbreaker/9999 慢响应
        // http://localhost:10005/feign/resilience4j/circuitbreaker/6 正常
        return api.myCircuit(id);
    }

    public String myCircuitFallback(Integer id, Throwable t) {
        // 断路器降级
        return "断路器降级," + port;
    }

    @GetMapping(value = "/feign/resilience4j/bulkhead/{id}")
    @Bulkhead(name = "cloud-service1", fallbackMethod = "myBulkheadFallback", type = Bulkhead.Type.SEMAPHORE)
    public String myBulkhead(@PathVariable("id") Integer id) throws InterruptedException {
        // Resilience4j Bulkhead 舱壁隔离 基于semaphore(信号量)实现
        // http://localhost:10005/feign/resilience4j/bulkhead/0 抛异常
        // http://localhost:10005/feign/resilience4j/bulkhead/9999 慢响应
        // http://localhost:10005/feign/resilience4j/bulkhead/6 正常
        return api.myBulkhead(id);
    }

    public String myBulkheadFallback(Throwable t) {
        return "舱壁隔离," + port;
    }

    @GetMapping(value = "/feign/resilience4j/bulkhead/threadpool/{id}")
    @Bulkhead(name = "cloud-service1", fallbackMethod = "myBulkheadPoolFallback", type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture<String> myBulkheadThreadpool(@PathVariable("id") Integer id) throws InterruptedException {
        // Resilience4j Bulkhead 舱壁隔离 线程池方式
        // http://localhost:10005/feign/resilience4j/bulkhead/threadpool/0 抛异常
        // http://localhost:10005/feign/resilience4j/bulkhead/threadpool/9999 慢响应
        // http://localhost:10005/feign/resilience4j/bulkhead/threadpool/6 正常
        System.out.println(Thread.currentThread().getName() + "\t" + "---开始进入");
        TimeUnit.SECONDS.sleep(3);
        System.out.println(Thread.currentThread().getName() + "\t" + "---准备离开");

        return CompletableFuture.supplyAsync(() -> {
            try {
                return api.myBulkhead(id) + "\t" + "Bulkhead.Type.THREADPOOL";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public CompletableFuture<String> myBulkheadPoolFallback(Integer id, Throwable t) {
        return CompletableFuture.supplyAsync(() -> "舱壁隔离," + port);
    }

    @GetMapping(value = "/feign/resilience4j/ratelimiter/{id}")
    @RateLimiter(name = "cloud-service1", fallbackMethod = "myRatelimitFallback")
    public String myRatelimit(@PathVariable("id") Integer id) {
        // Resilience4j RateLimiter 限流
        // http://localhost:10005/feign/resilience4j/ratelimiter/6 正常
        return api.myRatelimit(id);
    }

    public String myRatelimitFallback(Integer id, Throwable t) {
        return "限流," + port;
    }
}