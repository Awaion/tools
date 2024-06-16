package com.awaion.demo023;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Demo023Controller {

    @GetMapping("/hello")
    public String hello(){
        return "hello native-image";
    }
}
