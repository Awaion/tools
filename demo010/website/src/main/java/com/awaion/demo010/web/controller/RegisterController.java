package com.awaion.demo010.web.controller;

import com.awaion.demo010.base.domain.Logininfo;
import com.awaion.demo010.base.service.ILogininfoService;
import com.awaion.demo010.page.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RegisterController {

    //返回ajax类型对应的json字符串

    @Autowired
    private ILogininfoService logininfoService;


    @RequestMapping("/register.do")
    @ResponseBody
    public AjaxResult register(String username, String password) {

        AjaxResult result = new AjaxResult();

        try {
            /*System.out.println(1/0);*/
            logininfoService.register(username, password);  //注册成功的情况
        } catch (RuntimeException re) {
            result = new AjaxResult(re.getMessage()); //将错误信息给前台
        }
        return result;
    }


    @RequestMapping("/checkUsername.do")
    @ResponseBody
    public boolean checkUsername(String username) {

        return logininfoService.checkUsername(username);  //登录成功的情况
    }

    @RequestMapping("/login.do")
    @ResponseBody
    public AjaxResult login(String username, String password, HttpServletRequest request) {

        AjaxResult result = new AjaxResult();

        //是前台用户,所有登录的时候设置TYPE_CLIENT--->传递给service
        Logininfo currentlogininfo = logininfoService.login(username, password, request.getRemoteAddr(), Logininfo.TYPE_CLIENT);//登录成功的情况  -->将登录者的ip传递给login方法

        result.setMsg("登录成功");
        if (currentlogininfo == null) {
            result = new AjaxResult("用户名或者密码错误!");
        }
        return result;
    }

}
