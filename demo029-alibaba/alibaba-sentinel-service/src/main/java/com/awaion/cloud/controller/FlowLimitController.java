package com.awaion.cloud.controller;

import com.awaion.cloud.service.FlowLimitService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA() {
        // http://localhost:10017/testA
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB() {
        // http://localhost:10017/testB
        return "------testB";
    }

    @Resource
    private FlowLimitService flowLimitService;

    @GetMapping("/testC")
    public String testC() {
        // http://localhost:10017/testC
        // 流控-链路演示demo C和D两个请求都访问flowLimitService.common()方法，阈值到达后对C限流，对D不管
        flowLimitService.common();
        return "------testC";
    }

    @GetMapping("/testD")
    public String testD() {
        // http://localhost:10017/testD
        flowLimitService.common();
        return "------testD";
    }

    @GetMapping("/testE")
    public String testE() {
        // http://localhost:10017/testE
        System.out.println(System.currentTimeMillis() + "      testE,流控效果----排队等待");
        return "------testE";
    }

    @GetMapping("/testF")
    public String testF() {
        // http://localhost:10017/testF
        //暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("----测试:新增熔断规则-慢调用比例 ");
        return "------testF 新增熔断规则-慢调用比例";
    }

    @GetMapping("/testG")
    public String testG() {
        // http://localhost:10017/testE
        System.out.println("----测试:新增熔断规则-异常比例 ");
        int age = 10 / 0;
        return "------testG,新增熔断规则-异常比例 ";
    }

    @GetMapping("/testH")
    public String testH() {
        // http://localhost:10017/testH
        System.out.println("----测试:新增熔断规则-异常数 ");
        int age = 10 / 0;
        return "------testH,新增熔断规则-异常数 ";
    }
}
