package com.awaion.demo008.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController extends BaseController {

    @RequestMapping("/login")
    public String login() {
        return "forward:/WEB-INF/views/login.jsp";
    }


}
