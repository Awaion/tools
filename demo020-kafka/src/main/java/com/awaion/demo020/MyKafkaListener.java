package com.awaion.demo020;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyKafkaListener {

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
}

