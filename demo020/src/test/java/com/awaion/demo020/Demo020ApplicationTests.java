package com.awaion.demo020;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.StopWatch;

import java.util.concurrent.CompletableFuture;

@Slf4j
@SpringBootTest
class Demo020ApplicationTests {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Test
    void contextLoads() {
        sendMsg();
    }

    private void sendMsg() {
        CompletableFuture[] futures = new CompletableFuture[10000];
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < 10000; i++) {
            // JUC
            CompletableFuture future = kafkaTemplate.send("demo0201", "key-" + i, "value-" + i);
            futures[i] = future;
        }
        CompletableFuture.allOf(futures).join();
        stopWatch.stop();
        long millis = stopWatch.getTotalTimeMillis();
        log.info("10000消息发送完成时间:{}", millis);

        CompletableFuture future = kafkaTemplate.send("demo0202", "person", new Person(1L, "张三", "xxx@qq.com"));
        future.join();
        log.info("消息发送成功...");
    }

}
