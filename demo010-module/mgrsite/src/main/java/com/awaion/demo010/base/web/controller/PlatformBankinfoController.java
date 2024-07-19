package com.awaion.demo010.base.web.controller;

import com.awaion.demo010.base.query.PlatFormBankInfoQueryObject;
import com.awaion.demo010.business.domain.PlatFormBankInfo;
import com.awaion.demo010.business.service.IPlatformBankInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PlatformBankinfoController {

    @Autowired
    IPlatformBankInfoService platformBankInfoService;

    @RequestMapping("companyBank_list")
    public String list(@ModelAttribute("qo") PlatFormBankInfoQueryObject qo, Model model) {

        model.addAttribute("pageResult", platformBankInfoService.advanQuery(qo));
        return "platformbankinfo/list";
    }

    @RequestMapping("companyBank_update")
    public String update(PlatFormBankInfo bk) {
        PlatFormBankInfo bk2 = bk;
        System.out.println(bk2);

        platformBankInfoService.updateByPrimaryKey(bk);
        return "redirect:companyBank_list.do";
    }


}
