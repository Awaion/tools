package com.awaion.demo010.web.controller;

import com.awaion.demo010.base.service.IAccountService;
import com.awaion.demo010.base.service.IUserinfoService;
import com.awaion.demo010.base.util.UserContext;
import com.awaion.demo010.business.service.IExpAccountService;
import com.awaion.demo010.page.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PersonalController {

    @Autowired
    IAccountService accountService;

    @Autowired
    IUserinfoService userinfoService;

    @Autowired
    IExpAccountService expAccountService;


    @RequestMapping("/personal.do")
    public String personal(Model model) {
        if (UserContext.getCurrentUser() != null) {
            model.addAttribute("expAccount", expAccountService.selectByPrimaryKey(UserContext.getCurrentUser().getId()));
            model.addAttribute("account", accountService.selectByPrimaryKey(UserContext.getCurrentUser().getId()));
            model.addAttribute("userinfo", userinfoService.selectByPrimaryKey(UserContext.getCurrentUser().getId()));
        }
        return "personal";
    }

    @RequestMapping("/bindPhone.do")
    @ResponseBody
    public AjaxResult bindPhone(String phoneNumber, String verifyCode) {

        AjaxResult result = new AjaxResult();
        try {
            userinfoService.bindPhone(phoneNumber, verifyCode);
            result.setMsg("绑定成功");
        } catch (RuntimeException e) {
            e.printStackTrace();
            result = new AjaxResult(e.getMessage());
        }
        return result;
    }


    //绑定邮箱的方法
    @RequestMapping("/bindEmail.do")
    public String bindEmail(String key, Model model) {

        AjaxResult result = new AjaxResult();
        try {
            userinfoService.bindEmail(key);
            result.setMsg("绑定成功");

        } catch (RuntimeException e) {
            e.printStackTrace();
            result = new AjaxResult(e.getMessage());
        }
        model.addAttribute("result", result);

        return "checkmail_result";
    }


}
