package com.awaion.demo010.base.web.controller;

import com.awaion.demo010.base.page.AjaxResult;
import com.awaion.demo010.base.query.RealAuthQueryObject;
import com.awaion.demo010.base.service.IRealAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RealAuthController {

    @Autowired
    IRealAuthService realAuthService;

    @RequestMapping("/realAuth.do")
    public String listRealAuth(Model model, @ModelAttribute("qo") RealAuthQueryObject qo) {

        model.addAttribute("pageResult", realAuthService.advanQuery(qo));
        return "/realAuth/list";
    }

    @RequestMapping("/realAuth_audit")
    @ResponseBody
    public AjaxResult realAuth_audit(Long id, String remark, int state) {
        AjaxResult result = new AjaxResult();

        try {
            realAuthService.realAuthAudit(id, remark, state);
            result.setMsg("操作成功");

        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("操作失败");
        }
        return result;
    }

}
