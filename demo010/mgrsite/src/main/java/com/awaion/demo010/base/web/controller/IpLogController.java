package com.awaion.demo010.base.web.controller;


import com.awaion.demo010.base.query.IpLogQueryObject;
import com.awaion.demo010.base.service.IIpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IpLogController {

    @Autowired
    IIpLogService iIpLogService;

    @RequestMapping("ipLog.do")
    public String ipLogList(@ModelAttribute("qo") IpLogQueryObject qo, Model model) {

        model.addAttribute("pageResult", iIpLogService.advanQuery(qo));
        return "ipLog/list";

    }

}
