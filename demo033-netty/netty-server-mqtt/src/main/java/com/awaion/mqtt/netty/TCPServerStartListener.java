package com.awaion.mqtt.netty;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TCPServerStartListener implements ApplicationRunner {
    @Resource
    TCPServer TCPServer;

    @Override
    public void run(ApplicationArguments args) {
        // 启动TCP服务
        TCPServer.start();
    }
}
