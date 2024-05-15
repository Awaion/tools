package com.awaion.demo010.web.controller;

import com.awaion.demo010.base.domain.UserInfo;
import com.awaion.demo010.base.service.ISystemDictionaryService;
import com.awaion.demo010.base.service.IUserinfoService;
import com.awaion.demo010.base.util.UserContext;
import com.awaion.demo010.page.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BasicInfoController {

    @Autowired
    private IUserinfoService userinfoService;

    @Autowired
    private ISystemDictionaryService systemDictionaryService;


    @RequestMapping("/basicInfo.do")
    public String basicInfo(Model model) {

        Long userId = UserContext.getCurrentUser().getId();
        UserInfo userInfo = userinfoService.selectByPrimaryKey(userId);

        model.addAttribute("userinfo", userInfo);
        model.addAttribute("educationBackgrounds", systemDictionaryService.listItemsBySn("educationBackground"));
        model.addAttribute("incomeGrades", systemDictionaryService.listItemsBySn("incomeGrade"));
        model.addAttribute("marriages", systemDictionaryService.listItemsBySn("marriage"));
        model.addAttribute("kidCounts", systemDictionaryService.listItemsBySn("kidCount"));
        model.addAttribute("houseConditions", systemDictionaryService.listItemsBySn("houseCondition"));

        return "userInfo";
    }


    @RequestMapping("/basicInfo_save.do")
    @ResponseBody
    public AjaxResult updateOrSave(UserInfo userInfo) {

        AjaxResult result = new AjaxResult();

        userinfoService.updateBasicInfo(userInfo);

        return result;

    }


}
