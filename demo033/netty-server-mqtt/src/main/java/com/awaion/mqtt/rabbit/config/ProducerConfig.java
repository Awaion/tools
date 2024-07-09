package com.awaion.mqtt.rabbit.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ProducerConfig {
    String bindKey = "project1.station1.*";

//    @Bean
//    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {
//        RabbitTemplate rabbitTemplate = new RabbitTemplate();
//        rabbitTemplate.setConnectionFactory(connectionFactory);
//        //开启Mandatory,触发回调函数
//        rabbitTemplate.setMandatory(true);
//        //ack
//        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
//            @Override
//            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//                log.info("-----------confirm begin---------------");
//                log.info("data:" + correlationData);
//                if (ack) {
//                    log.info("Ack:true");
//                } else {
//                    log.info("Ack:false");
//                }
//                log.info("cause:" + cause);
//                log.info("-----------confirm end---------------");
//            }
//        });
//        //return
//        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
//            @Override
//            public void returnedMessage(ReturnedMessage returnedMessage) {
//                log.info("-----------return begin---------------");
//                log.info("message:" + returnedMessage.getMessage());
//                log.info("reply code:" + returnedMessage.getReplyCode());
//                log.info("reply text:" + returnedMessage.getReplyText());
//                log.info("exchange:" + returnedMessage.getExchange());
//                log.info("routeKey:" + returnedMessage.getRoutingKey());
//                log.info("-----------return end---------------");
//            }
//        });
//
//        return rabbitTemplate;
//    }
//
//
//    @Bean("topic_exchange")
//    public TopicExchange topicExchange() {
//        return ExchangeBuilder.topicExchange("topic_exchange").durable(true).build();
//    }
//
//    @Bean("topic_queue")
//    public Queue topicQueue() {
//        return QueueBuilder.durable("topic_queue").build();
//    }
//
//    @Bean
//    public Binding topicBind() {
//        return BindingBuilder.bind(topicQueue()).to(topicExchange()).with(bindKey);
//    }


}
