package com.awaion.demo010.web.controller;

import com.awaion.demo010.base.domain.BidConsts;
import com.awaion.demo010.business.domain.BidRequest;
import com.awaion.demo010.business.query.BidRequestQueryObject;
import com.awaion.demo010.business.service.IBidRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {


    @Autowired
    private IBidRequestService bidRequestService;


    @RequestMapping("index")
    public String index(Model model) {

        List<BidRequest> bidRequests = bidRequestService.list5items();

        model.addAttribute("bidRequests", bidRequestService.list5items());

        System.out.println("方法进来了");

        return "main";
    }


    @RequestMapping("invest")
    public String invest() {
        return "invest";
    }


    @RequestMapping("invest_list")
    public String investList(Model model, BidRequestQueryObject qo) {

        if (qo.getBidRequestState() == -1) {
            qo.setStates(new int[]{BidConsts.BIDREQUEST_STATE_BIDDING, BidConsts.BIDREQUEST_STATE_PAYING_BACK, BidConsts.BIDREQUEST_STATE_COMPLETE_PAY_BACK});

        }
        model.addAttribute("pageResult", bidRequestService.advanQuery(qo));
        return "invest_list";
    }

}
