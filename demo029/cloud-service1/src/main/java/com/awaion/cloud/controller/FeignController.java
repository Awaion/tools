package com.awaion.cloud.controller;

import com.awaion.cloud.resp.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FeignController {
    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/feign/exception/{id}")
    public ResultData<String> exception(@PathVariable("id") Integer id) {
        // Feign 异常测试接口
        // http://localhost:10001/feign/exception/0 抛异常
        // http://localhost:10001/feign/exception/6 正常
        if (id == 0) throw new RuntimeException("参数异常");
        return ResultData.success(id + "," + port);
    }

    @GetMapping(value = "/feign/timeout/{id}")
    public ResultData<String> timeout(@PathVariable("id") Integer id) throws InterruptedException {
        // Feign 超时测试接口
        // http://localhost:10001/feign/timeout/0 超时
        // http://localhost:10001/feign/timeout/6 正常
        if (id == 0) TimeUnit.SECONDS.sleep(62);
        return ResultData.success(id + "," + port);
    }

    @GetMapping(value = "/feign/error/{id}")
    public ResultData<String> error(@PathVariable("id") Integer id) {
        // Feign 错误测试接口
        // http://localhost:10001/feign/error/0 错误
        // http://localhost:10001/feign/error/6 正常
        if (id == 0) System.out.println(10 / 0);
        return ResultData.success(id + "," + port);
    }

}
