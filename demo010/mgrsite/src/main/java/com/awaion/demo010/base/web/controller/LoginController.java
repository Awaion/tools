package com.awaion.demo010.base.web.controller;

import com.awaion.demo010.base.domain.Logininfo;
import com.awaion.demo010.base.page.AjaxResult;
import com.awaion.demo010.base.service.ILogininfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    //返回ajax类型对应的json字符串

    @Autowired
    private ILogininfoService logininfoService;


    @RequestMapping("/login.do")
    @ResponseBody
    public AjaxResult login(String username, String password, HttpServletRequest request) {

        AjaxResult result = new AjaxResult();
        Logininfo currentlogininfo = logininfoService.login(username, password, request.getRemoteAddr(), Logininfo.TYPE_MANAGER);//登录成功的情况  -->将登录者的ip传递给login方法
        result.setMsg("登录成功");

        if (currentlogininfo == null) {
            result = new AjaxResult("用户名或者密码错误!");
        }
        return result;
    }

}
