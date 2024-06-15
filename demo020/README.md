# SpringBoot 3 + Kafka 消息服务

## 代码地址

- Gitee: https://gitee.com/Awaion/tools/tree/master/demo020
- Github: https://github.com/Awaion/tools/tree/master/demo020

## 简介

如题

## 演示

生产消息 com.awaion.demo020.Demo020ApplicationTests

消费消息 main

## 实现

```
<dependency>
    <groupId>org.springframework.kafka</groupId>
    <artifactId>spring-kafka</artifactId>
</dependency>

@EnableKafka // 启动 kafka

@Bean
public NewTopic topic1() {
    // 创建 topic
    return TopicBuilder.name("demo0201")
            .partitions(1)
            .compact()
            .build();
}

@Bean
public NewTopic topic2() {
    // 创建 topic
    return TopicBuilder.name("demo0202")
            .partitions(1)
            .compact()
            .build();
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

// 默认的监听是从消息队列最后一个消息开始拿,新消息才能拿到
@KafkaListener(topics = "demo0201", groupId = "group01")
public void listener1(ConsumerRecord record) {
    Object key = record.key();
    Object value = record.value();
    log.info("topics:demo0201=1=,接收消息,key:{},value:{}", key, value);
}

@KafkaListener(topics = "demo0201", groupId = "group01")
public void listener3(ConsumerRecord record) {
    Object key = record.key();
    Object value = record.value();
    log.info("topics:demo0201=2=,接收消息,key:{},value:{}", key, value);
}

// 拿到以前的完整消息
@KafkaListener(
        groupId = "group01",
        topicPartitions = {
                @TopicPartition(topic = "demo0202",
                        partitionOffsets = {
                                @PartitionOffset(partition = "0", initialOffset = "0")
                        })
        })
public void listener2(ConsumerRecord record) {
    Object key = record.key();
    Object value = record.value();
    log.info("topics:demo0202,接收消息,key:{},value:{}", key, value);
}
```

## 技术说明

#### 后端

| 技术           | 说明                | 官网                                           |
|--------------| ------------------- | ---------------------------------------------- |
| SpringBoot 3 | SpringBoot     | https://spring.io/projects/spring-boot         |
| Spring Kafka | Spring Kafka     | https://spring.io/projects/spring-kafka         |

#### 开发工具

| 工具          | 说明                | 官网                                            |
| ------------- | ------------------- | ----------------------------------------------- |
| IDEA          | 开发IDE             | https://www.jetbrains.com/idea/download         |

#### 开发环境

| 工具     | 版本号  | 下载                                                                                 |
|--------| ------ | ------------------------------------------------------------                         |
| JDK  | 17  | https://www.oracle.com/java/technologies/downloads/#java17 |

#### 启动方式

生产消息 com.awaion.demo020.Demo020ApplicationTests

消费消息 main

## 许可证

[MIT License](https://opensource.org/license/mit)

Copyright (c) 2024-2024 Awaion

