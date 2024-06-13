package com.awaion.demo015.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * 配置到 META-INF/spring.factories 自动加载
 */
@Slf4j
public class MyApplicationListener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        log.info("===事件感知===MyApplicationListener:onApplicationEvent==={}===", event.toString());
    }

    @Override
    public boolean supportsAsyncExecution() {
        log.info("===异步事件感知===MyApplicationListener:onApplicationEvent===");
        return ApplicationListener.super.supportsAsyncExecution();
    }
}
