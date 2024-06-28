package com.awaion.cloud.controller;

import com.awaion.cloud.apis.AlibabaService1FeignApi;
import com.awaion.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AlibabaService3Controller {
    @Resource
    private RestTemplate restTemplate;

    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/consumer/pay/nacos/{id}")
    public String paymentInfo(@PathVariable("id") Integer id) {
        // http://localhost:10013/consumer/pay/nacos/3
        String result = restTemplate.getForObject("http://alibaba-service1/pay/nacos/" + id, String.class);
        return "消费者:" + port + ",提供者:" + result;
    }

    //===================================
    @Resource
    private AlibabaService1FeignApi alibabaService1FeignApi;

    @GetMapping(value = "/consumer/pay/nacos/get/{orderNo}")
    public ResultData getPayByOrderNo(@PathVariable("orderNo") String orderNo) {
        // http://localhost:10013/consumer/pay/nacos/get/qwe
        return alibabaService1FeignApi.getPayByOrderNo(orderNo);
    }


}

