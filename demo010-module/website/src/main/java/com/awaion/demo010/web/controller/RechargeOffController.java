package com.awaion.demo010.web.controller;

import com.awaion.demo010.business.domain.RechargeOffline;
import com.awaion.demo010.business.service.IPlatformBankInfoService;
import com.awaion.demo010.business.service.IRechargeOfflineService;
import com.awaion.demo010.page.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RechargeOffController { //充值控制器

    @Autowired
    private IRechargeOfflineService rechargeOfflineService;
    @Autowired
    private IPlatformBankInfoService platformBankInfoService;

    @RequestMapping("recharge")
    public String recharge(Model model) {

        model.addAttribute("banks", platformBankInfoService.selectAll());
        return "recharge";
    }

    @RequestMapping("recharge_save")
    @ResponseBody
    public AjaxResult apply(RechargeOffline ro) {
        rechargeOfflineService.apply(ro);

        return new AjaxResult();
    }


}
