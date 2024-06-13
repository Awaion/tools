package com.awaion.demo015.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.time.Duration;

/**
 * 配置到 META-INF/spring.factories 自动加载
 */
@Slf4j
public class MySpringApplicationRunListener implements SpringApplicationRunListener {
    @Override
    public void starting(ConfigurableBootstrapContext bootstrapContext) {
        log.info("===引导阶段===MySpringApplicationRunListener:starting===正在启动===");
    }

    @Override
    public void environmentPrepared(ConfigurableBootstrapContext bootstrapContext, ConfigurableEnvironment environment) {
        log.info("===引导阶段===MySpringApplicationRunListener:environmentPrepared===环境准备完成===");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        log.info("===启动阶段===MySpringApplicationRunListener:contextPrepared===ioc容器准备完成===");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        log.info("===启动阶段===MySpringApplicationRunListener:contextLoaded===ioc容器加载完成===");
    }

    @Override
    public void started(ConfigurableApplicationContext context, Duration timeTaken) {
        log.info("===启动阶段===MySpringApplicationRunListener:started===启动完成===");
    }

    @Override
    public void ready(ConfigurableApplicationContext context, Duration timeTaken) {
        log.info("===启动阶段===MySpringApplicationRunListener:ready===准备就绪===");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        log.info("===启动失败===MySpringApplicationRunListener:failed===应用启动失败===");
    }
}
