package com.awaion.demo015.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 配置到 META-INF/spring.factories 自动加载
 */
@Slf4j
public class MyApplicationContextInitializer implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        log.info("===IOC初始化===MyApplicationContextInitializer:initialize==={}===", applicationContext.toString());
    }
}
