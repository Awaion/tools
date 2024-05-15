package com.awaion.demo010.base.web.controller;


import com.awaion.demo010.base.page.AjaxResult;
import com.awaion.demo010.base.query.PageResult;
import com.awaion.demo010.business.query.RechargeOfflineQueryObject;
import com.awaion.demo010.business.service.IPlatformBankInfoService;
import com.awaion.demo010.business.service.IRechargeOfflineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RechargeOfflineController {

    @Autowired
    private IPlatformBankInfoService platformBankInfoService;

    @Autowired
    private IRechargeOfflineService rechargeOfflineService;

    @RequestMapping("rechargeOffline")
    public String rechargeList(@ModelAttribute("qo") RechargeOfflineQueryObject qo, Model model) {

        PageResult result = rechargeOfflineService.advanQuery(qo);

        model.addAttribute("banks", platformBankInfoService.selectAll());
        model.addAttribute("pageResult", rechargeOfflineService.advanQuery(qo));
        return "rechargeoffline/list";
    }

    @RequestMapping("rechargeOffline_audit")
    @ResponseBody
    public AjaxResult audit(Long id, String remark, int state) {
        rechargeOfflineService.audit(id, remark, state);
        return new AjaxResult();
    }


}
