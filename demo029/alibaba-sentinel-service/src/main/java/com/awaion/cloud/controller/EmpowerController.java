package com.awaion.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class EmpowerController //Empower授权规则，用来处理请求的来源
{
    @GetMapping(value = "/empower")
    public String requestSentinel4() {
        // http://localhost:10017/empower?serverName=test1
        log.info("测试Sentinel授权规则empower");
        return "Sentinel授权规则";
    }
}
