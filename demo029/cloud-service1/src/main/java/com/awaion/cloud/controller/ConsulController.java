package com.awaion.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ConsulController {
    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/consul/config")
    public String config(@Value("${awaion.info:aa}") String awaionInfo) {
        // Consul 配置获取 http://localhost:10001/consul/config?arg0=qw
        return "awaionInfo: " + awaionInfo + "\t" + "port: " + port;
    }

}
