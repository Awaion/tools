package com.awaion.demo015.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

/**
 * 配置到 META-INF/spring.factories 自动加载
 */
@Slf4j
public class MyApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("===运行===MyApplicationRunner:run==={}===", args.toString());
    }
}
