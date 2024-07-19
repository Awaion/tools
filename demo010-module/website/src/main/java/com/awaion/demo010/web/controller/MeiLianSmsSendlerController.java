package com.awaion.demo010.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MeiLianSmsSendlerController {


    @RequestMapping("/sendSms.do")
    @ResponseBody
    public String SendSms(String username, String password, String content, String APIkey) {
        System.out.println("网关端:短信发送成功-->" + content);
        return "success:msgid";
    }

}
