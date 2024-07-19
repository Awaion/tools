package com.awaion.demo008.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController extends BaseController {
    @RequestMapping("/main")
    public String main() {
        return "main";
    }
}
