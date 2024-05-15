package com.awaion.demo010.base.web.controller;

import com.awaion.demo010.base.domain.SystemDictionary;
import com.awaion.demo010.base.domain.SystemDictionaryItem;
import com.awaion.demo010.base.page.AjaxResult;
import com.awaion.demo010.base.query.SystemDictionaryQueryObject;
import com.awaion.demo010.base.service.ISystemDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SystemDictionaryController {

    @Autowired
    ISystemDictionaryService systemDictionaryService;


    @RequestMapping("/systemDictionaryItem_list.do")
    public String systemDictionaryItem_list(@ModelAttribute("qo") SystemDictionaryQueryObject qo, Model model) {


        model.addAttribute("systemDictionaryGroups", systemDictionaryService.listAllDics());
        model.addAttribute("pageResult", systemDictionaryService.advanQueryItem(qo));

        return "/systemdic/systemDictionaryItem_list";

    }


    @RequestMapping("/systemDictionaryItem_update.do")
    @ResponseBody
    public AjaxResult systemDictionaryItem_update(SystemDictionaryItem item) {

        AjaxResult result = new AjaxResult();
        try {
            result.setMsg("保存成功");
            systemDictionaryService.saveOrUpdateItem(item);
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("保存失败");
        }
        return result;
    }


    //=========================================================


    @RequestMapping("/systemDictionary_list.do")
    public String systemDictionary_list(@ModelAttribute("qo") SystemDictionaryQueryObject qo, Model model) {

        model.addAttribute("pageResult", systemDictionaryService.advanQuery(qo));

        return "/systemdic/systemDictionary_list";

    }


    @RequestMapping("/systemDictionary_update.do")
    @ResponseBody
    public AjaxResult systemDictionary_update(SystemDictionary sd) {

        AjaxResult result = new AjaxResult();
        try {
            result.setMsg("保存成功");
            systemDictionaryService.saveOrUpdate(sd);
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("保存失败");
        }
        return result;
    }

}
