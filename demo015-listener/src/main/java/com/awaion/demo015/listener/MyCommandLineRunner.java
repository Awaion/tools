package com.awaion.demo015.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;

import java.util.Arrays;

/**
 * 配置到 META-INF/spring.factories 自动加载
 */
@Slf4j
public class MyCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.info("===命令行===MyCommandLineRunner:run==={}===", Arrays.asList(args));
    }
}
