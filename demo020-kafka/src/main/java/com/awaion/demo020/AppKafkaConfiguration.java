package com.awaion.demo020;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class AppKafkaConfiguration {

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

}

