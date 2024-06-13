package com.awaion.demo015.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.BootstrapRegistry;
import org.springframework.boot.BootstrapRegistryInitializer;

/**
 * 配置到 META-INF/spring.factories 自动加载
 */
@Slf4j
public class MyBootstrapRegistryInitializer implements BootstrapRegistryInitializer {
    @Override
    public void initialize(BootstrapRegistry registry) {
        log.info("===启动初始化===MyBootstrapRegistryInitializer:initialize==={}===", registry.toString());
    }
}
