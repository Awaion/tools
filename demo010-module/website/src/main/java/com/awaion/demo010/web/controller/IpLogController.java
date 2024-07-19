package com.awaion.demo010.web.controller;

import com.awaion.demo010.base.domain.Logininfo;
import com.awaion.demo010.base.query.IpLogQueryObject;
import com.awaion.demo010.base.service.IIpLogService;
import com.awaion.demo010.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IpLogController {

    @Autowired
    IIpLogService iIpLogService;

    @RequestMapping("/ipLog.do")
    public String ipLoglist(Model model, IpLogQueryObject qo) {

        /*model.addAttribute("account", accountService.selectByPrimaryKey(UserContext.getCurrentUser().getId()));
        model.addAttribute("userinfo", userinfoService.selectByPrimaryKey(UserContext.getCurrentUser().getId()));*/
        qo.setUsername(UserContext.getCurrentUser().getUsername());


        qo.setUserType(Logininfo.TYPE_CLIENT);

        model.addAttribute("qo", qo);
        model.addAttribute("pageResult", iIpLogService.advanQuery(qo));

        return "iplog_list";
    }


}
