package com.awaion.demo034;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.random.RandomGenerator;

@RestController
public class Demo034Controller {
    private Demo034Service service;

    private ExecutorService threadPool;

    public Demo034Controller(Demo034Service service) {
        this.service = service;
        this.threadPool = Executors.newFixedThreadPool(50);
    }

    @GetMapping("/distributedLock")
    public String distributedLock() throws InterruptedException {
        // http://localhost:8080/distributedLock 分布式锁 setnx + expire
        CountDownLatch countDownLatch = new CountDownLatch(50);
        for (int i = 0; i < 50; i++) {
            CompletableFuture.runAsync(() -> {
                service.distributedReentrantLock();
                countDownLatch.countDown();
            }, threadPool);
        }
        countDownLatch.await();
        return "Hello World ->" + service.getCounter();
    }

    @GetMapping("/getCache")
    public Mono<String> getCache(String key) {
        // http://localhost:8080/getCache?key=key 缓存 map
        return Mono.just(service.getCache(key));
    }

    @GetMapping("/list")
    public Mono<String> list(String key) {
        // http://localhost:8080/list?key=key 消息队列 list
        return Mono.just(service.list(key));
    }


    @GetMapping("/userCount")
    public Mono<Long> userCount() throws InterruptedException {
        // http://localhost:8080/userCount 在线用户计数 set
        CountDownLatch countDownLatch = new CountDownLatch(50);
        for (int i = 0; i < 50; i++) {
            int finalI = i;
            CompletableFuture.runAsync(() -> {
                service.userLogin(String.format("user %d", finalI));
                countDownLatch.countDown();
            }, threadPool);
        }
        countDownLatch.await();
        return Mono.just(service.getOnlineUserCount());
    }

    @GetMapping("/getTopNUsers")
    public Mono<List<String>> getTopNUsers(Integer number) throws InterruptedException {
        // http://localhost:8080/getTopNUsers?number=50 排行榜 zset
        CountDownLatch countDownLatch = new CountDownLatch(50);
        for (int i = 0; i < 50; i++) {
            int finalI = i;
            CompletableFuture.runAsync(() -> {
                service.addUserToLeaderboard(String.format("user %d", finalI), RandomGenerator.getDefault().nextDouble() * 100);
                countDownLatch.countDown();
            }, threadPool);
        }
        countDownLatch.await();
        return Mono.just(service.getTopNUsers(number));
    }

    @GetMapping("/rateLimiter")
    public Mono<String> rateLimiter() throws InterruptedException {
        // http://localhost:8080/rateLimiter 限流 map
        CountDownLatch countDownLatch = new CountDownLatch(50);
        AtomicInteger successNum = new AtomicInteger();
        AtomicInteger failNum = new AtomicInteger();
        for (int i = 0; i < 50; i++) {
            int userId = 3;
            CompletableFuture.supplyAsync(() -> {
                countDownLatch.countDown();
                return service.rateLimiter(String.format("user %d", userId));
            }, threadPool).whenComplete((res, ex) -> {
                System.out.println(res);
                if (res) {
                    System.out.println(successNum.incrementAndGet());
                } else {
                    System.out.println(failNum.incrementAndGet());
                }
            });
        }
        countDownLatch.await();
        return Mono.just(String.format("成功次数 %d，失败次数 %d", successNum.get(), failNum.get()));
    }

    @GetMapping("/getPV")
    public Mono<String> getPV() throws InterruptedException {
        // http://localhost:8080/getPV 实时PV统计 map
        CountDownLatch countDownLatch = new CountDownLatch(50);
        for (int i = 0; i < 50; i++) {
            CompletableFuture.runAsync(() -> {
                service.incrementPV();
                countDownLatch.countDown();
            }, threadPool);
        }
        countDownLatch.await();
        return Mono.just(service.getPV());
    }

    // 发布订阅


}
