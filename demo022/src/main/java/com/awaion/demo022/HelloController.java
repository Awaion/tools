package com.awaion.demo022;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    MyMeterComponent myMeterComponent;

    @GetMapping("/hello")
    public String hello() {
        myMeterComponent.myMeterCounter();
        return "success";
    }
}

