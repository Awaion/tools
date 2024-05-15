package com.awaion.demo010.base.web.controller;

import com.awaion.demo010.base.page.AjaxResult;
import com.awaion.demo010.base.query.VideoAuthQueryObject;
import com.awaion.demo010.base.service.IVideoAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class VideoAuthController {

    @Autowired
    IVideoAuthService videoAuthService;

    @RequestMapping("/vedioAuth")
    public String vedioAuth(Model model, @ModelAttribute("qo") VideoAuthQueryObject qo) {

        model.addAttribute("pageResult", videoAuthService.advanQuery(qo));

        return "vedioAuth/list";
    }


    @RequestMapping("/vedioAuth_audit")
    @ResponseBody
    public AjaxResult vedioAuthAudit(int state, String remark, int loginInfoValue) {

        AjaxResult result = new AjaxResult();

        //todo:调审核方法--->service建立对应方法
        try {
            videoAuthService.vedioAuthAudit(state, remark, loginInfoValue);
        } catch (Exception e) {
            result = new AjaxResult("审核操作失败");
            e.printStackTrace();
        }
        return result;
    }
}
