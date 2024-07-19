package com.awaken.tool.demo001.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Demo001Controller {

    @GetMapping("/api")
    public String api() {
        return "api";
    }

}
