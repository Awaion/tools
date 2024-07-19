package com.awaion.cloud.controller;

import com.awaion.cloud.apis.CloudService1Api;
import com.awaion.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoadBalancedController {
    @Resource
    private CloudService1Api api;

    @PostMapping(value = "/feign/feign/error")
    public ResultData addOrder(@PathVariable("id") Integer id) {
        // Feign 错误测试接口
        // http://localhost:10005/feign/feign/error/0 错误
        // http://localhost:10005/feign/feign/error/6 正常
        return ResultData.success(api.error(id));
    }

    @GetMapping(value = "/feign/feign/error/lb")
    public ResultData loadBalanced(@PathVariable("id") Integer id) {
        // Feign 错误测试接口
        // http://localhost:10005/feign/feign/error/lb/0 错误
        // http://localhost:10005/feign/feign/error/lb/6 正常
        return ResultData.success(api.error(id));
    }

}