package com.awaion.cloud.controller;

import com.awaion.cloud.apis.CloudService1Api;
import com.awaion.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderMicrometerController {
    @Resource
    private CloudService1Api api;
    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/feign/micrometer/pay/{id}")
    public ResultData<String> myMicrometer(@PathVariable("id") Integer id) {
        // http://localhost:10005/feign/micrometer/pay/1 Micrometer(Sleuth) 链路监控例子
        log.info("Micrometer id:{},port:{}", id, port);
        return api.myMicrometer(id);
    }
}