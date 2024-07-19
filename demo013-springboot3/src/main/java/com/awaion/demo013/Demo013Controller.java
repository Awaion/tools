package com.awaion.demo013;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Demo013Controller {

    @GetMapping("/api")
    public String api(){
        return "Hello World";
    }
}
