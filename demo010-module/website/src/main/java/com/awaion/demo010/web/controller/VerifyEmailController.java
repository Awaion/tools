package com.awaion.demo010.web.controller;

import com.awaion.demo010.base.service.IVerifyMailService;
import com.awaion.demo010.page.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VerifyEmailController {

    @Autowired
    private IVerifyMailService verifyMailService;

    @RequestMapping("/sendVerifyEmail.do")
    @ResponseBody
    public AjaxResult sendVerifyEmail(String email) {
        AjaxResult result = new AjaxResult();
        //发送验证邮件
        try {
            verifyMailService.sendVerifyEmail(email);
            result.setMsg("发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(e.getMessage());
        }
        return result;
    }
}
