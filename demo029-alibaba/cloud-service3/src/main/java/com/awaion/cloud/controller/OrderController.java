package com.awaion.cloud.controller;

import cn.hutool.json.JSONUtil;
import com.awaion.cloud.entities.PayDTO;
import com.awaion.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class OrderController {
    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private RestTemplate restTemplate;

    @PostMapping(value = "/order/add")
    public ResultData addOrder(@RequestBody PayDTO payDTO) {// 不添加 @RequestBody 传JSON也可以
        // http://localhost:10003/order/add {"payNo":"payNo001","orderNo":"orderNo001","userId":1,"amount":"6.66"}
        System.out.println("订单添加:" + JSONUtil.toJsonStr(payDTO));
        return restTemplate.postForObject("http://cloud-service1/pay/add", payDTO, ResultData.class);
    }

    @GetMapping(value = "/order/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id) {
        // http://localhost:10003/order/get/2
        // 服务注册中心上的微服务名称
        return restTemplate.getForObject("http://cloud-service1/pay/get/" + id, ResultData.class, id);
    }

    @GetMapping("/order/discovery")
    public String discovery() {
        // http://localhost:10003/order/discovery
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            System.out.println(element);
        }

        System.out.println("===================================");

        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-service1");

        StringBuilder sb = new StringBuilder();
        for (ServiceInstance element : instances) {
            String str = element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t" + element.getUri() + "\n";
            sb.append(str);
            System.out.println(str);
        }

        return sb.toString();
    }

}
