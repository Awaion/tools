package com.awaion.demo010.base.web.controller;

import com.awaion.demo010.base.page.AjaxResult;
import com.awaion.demo010.base.query.PageResult;
import com.awaion.demo010.base.query.UserFileQueryObject;
import com.awaion.demo010.base.service.IUserFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserFileController {

    @Autowired
    IUserFileService userFileService;


    @RequestMapping("/userFileAuth")
    public String userFileAuth(@ModelAttribute("qo") UserFileQueryObject qo, Model model) {

        PageResult pageResult = userFileService.advanQuery(qo);

        model.addAttribute("pageResult", pageResult);

        return "/userFileAuth/list";
    }

    @RequestMapping("/userFile_audit")
    @ResponseBody
    public AjaxResult realAuth_audit(Long id, String remark, int score, int state) {
        AjaxResult result = new AjaxResult();
        try {
            userFileService.userFileAudit(id, remark, score, state);
            result.setMsg("操作成功");

        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("操作失败");
        }
        return result;
    }


}
