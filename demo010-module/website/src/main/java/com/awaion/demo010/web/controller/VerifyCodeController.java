package com.awaion.demo010.web.controller;

import com.awaion.demo010.base.service.IVerifyCodeService;
import com.awaion.demo010.page.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VerifyCodeController {


    @Autowired
    private IVerifyCodeService verifyCodeService;

    @RequestMapping("/sendVerifyCode.do")
    @ResponseBody
    public AjaxResult sendVerifyCode(String phoneNumber) {

        System.out.println("当前手机号:" + phoneNumber);

        AjaxResult jsonresult = new AjaxResult();
        jsonresult.setMsg("发送成功");

        verifyCodeService.sendVerifyCode(phoneNumber);

        return jsonresult;
    }


//    sendVerifyCode.do


}
