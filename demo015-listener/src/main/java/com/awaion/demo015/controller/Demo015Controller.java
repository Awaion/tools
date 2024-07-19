package com.awaion.demo015.controller;

import com.awaion.demo015.publisher.MyEvent;
import com.awaion.demo015.publisher.MyEventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class Demo015Controller {

    @Autowired
    private MyEventPublisher publisher;

    /**
     * 请求 http://localhost:8080/register?username=%E5%BC%A0%E4%B8%89&password=fwkt
     */
    @GetMapping("/register")
    public String register(String username, String password) {
        log.info("===注册===Demo015Controller:register===username:{}===password:{}", username, password);
        // 发布注册成功事件
        publisher.mySendEvent(new MyEvent("username:" + username + ",password:" + password));
        return "注册成功";
    }
}
