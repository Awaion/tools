package com.awaion.demo016.controller;

import com.awaion.demo016.service.Demo016Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class Demo016AutoController {
    @Autowired
    private Demo016Service service;

    /**
     * http://localhost:8080/demo016/auto/getInfo
     * http://localhost:8081/demo016/auto/getInfo
     */
    @GetMapping("/demo016/auto/getInfo")
    public String getInfo() {
        return service.getInfo()+service.getInfo();
    }
}
