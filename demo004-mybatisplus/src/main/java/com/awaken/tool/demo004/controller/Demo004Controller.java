package com.awaken.tool.demo004.controller;

import com.awaken.tool.demo004.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/my-batis-plus")
@Tag(name = "MyBatisPlus简单使用")
public class Demo004Controller {
    @Autowired
    private UserService service;

    @GetMapping("/test-add")
    public String testAdd(){
        // http://localhost:8080/my-batis-plus/test-add
        service.add1();
        return "Hello World";
    }

}
