package com.awaion.mqtt.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProducerService {
    String exchange = "topic_exchange";
    String routeKey = "project1.station1.device1";
//    @Resource
//    private RabbitTemplate rabbitTemplate;

    public void sendData(String data) {
//        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        log.info("===topic producer begin------------");
//        rabbitTemplate.convertAndSend(exchange, routeKey, data);
//        log.info("exchange:"+exchange);
//        log.info("routeKey:"+routeKey);
//        log.info("send data:"+data);
        log.info("===topic producer end------------");
    }

}
