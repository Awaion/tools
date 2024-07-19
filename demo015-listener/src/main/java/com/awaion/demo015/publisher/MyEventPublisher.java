package com.awaion.demo015.publisher;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

@Service
public class MyEventPublisher implements ApplicationEventPublisherAware {

    ApplicationEventPublisher applicationEventPublisher;

    public void mySendEvent(ApplicationEvent event) {
        // 发布事件
        applicationEventPublisher.publishEvent(event);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
