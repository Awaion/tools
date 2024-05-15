package com.awaion.demo010.base.web.controller;


import com.awaion.demo010.base.domain.BidConsts;
import com.awaion.demo010.base.domain.UserFile;
import com.awaion.demo010.base.domain.UserInfo;
import com.awaion.demo010.base.page.AjaxResult;
import com.awaion.demo010.base.query.UserFileQueryObject;
import com.awaion.demo010.base.service.IRealAuthService;
import com.awaion.demo010.base.service.IUserFileService;
import com.awaion.demo010.base.service.IUserinfoService;
import com.awaion.demo010.business.domain.BidRequest;
import com.awaion.demo010.business.domain.BidRequestAudit;
import com.awaion.demo010.business.query.BidRequestQueryObject;
import com.awaion.demo010.business.service.IBidRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BidRequestController {


    @Autowired
    private IBidRequestService bidRequestService;

    @Autowired
    private IUserinfoService userinfoService;

    @Autowired
    private IUserFileService userFileService;


    @Autowired
    private IRealAuthService realAuthService;

    //去发标前审核列表
    @RequestMapping("/bidrequest_publishaudit_list")
    public String bidRequestAuditList(@ModelAttribute("qo") BidRequestQueryObject qo, Model model) {

        qo.setBidRequestState(BidConsts.BIDREQUEST_STATE_PUBLISH_PENDING);
        model.addAttribute("pageResult", bidRequestService.advanQuery(qo));
        return "bidrequest/publish_audit";
    }

    //发飙前审核
    @RequestMapping("/bidrequest_publishaudit")
    @ResponseBody
    public AjaxResult publishAudit(int id, String remark, int state) {

        AjaxResult result = new AjaxResult();

        try {
            bidRequestService.publishAudit(id, remark, state);
        } catch (Exception e) {
            result = new AjaxResult("操作异常");
            e.printStackTrace();
        }
        return result;
    }


    //去满标一审的列表页面
    @RequestMapping("/bidrequest_audit1_list")
    public String bidrequestAudit1List(@ModelAttribute("qo") BidRequestQueryObject qo, Model model) {
        qo.setBidRequestState(BidConsts.BIDREQUEST_STATE_APPROVE_PENDING_1);
        model.addAttribute("pageResult", bidRequestService.advanQuery(qo));
        return "bidrequest/audit1";
    }

    //满标一审的审核操作
    @RequestMapping("/bidrequest_audit1")
    @ResponseBody
    public AjaxResult fullAudit1(int id, String remark, int state) {

        AjaxResult result = new AjaxResult();

        try {
            bidRequestService.fullAudit1(id, remark, state);
        } catch (Exception e) {
            result = new AjaxResult("操作异常");
            e.printStackTrace();
        }
        return result;
    }

    //-------------------满标二审-------------------
    //去满标二审的列表页面
    @RequestMapping("/bidrequest_audit2_list")
    public String bidrequestAudit2List(@ModelAttribute("qo") BidRequestQueryObject qo, Model model) {
        qo.setBidRequestState(BidConsts.BIDREQUEST_STATE_APPROVE_PENDING_2);
        model.addAttribute("pageResult", bidRequestService.advanQuery(qo));
        return "bidrequest/audit2";
    }

    //满标一审的审核操作
    @RequestMapping("/bidrequest_audit2")
    @ResponseBody
    public AjaxResult fullAudit2(int id, String remark, int state) {

        AjaxResult result = new AjaxResult();

        try {
            bidRequestService.fullAudit2(id, remark, state);
        } catch (Exception e) {
            result = new AjaxResult("操作异常");
            e.printStackTrace();
        }
        return result;
    }

    //-------------------满标二审-------------------


    //借款详情页

    @RequestMapping("/borrow_info")
    public String borrowDetail(Long id, Model model) {

        BidRequest bidRequest = bidRequestService.selectByPrimaryKey(id);

        if (bidRequest != null) {
            UserInfo userInfo = userinfoService.selectByPrimaryKey(bidRequest.getCreateUser().getId());
            model.addAttribute("userInfo", userInfo);  //借款人信息
            model.addAttribute("bidRequest", bidRequest); //借款对象
            model.addAttribute("realAuth", realAuthService.selectByPrimaryKey(userInfo.getRealAuthId()));
            List<BidRequestAudit> audits = bidRequestService.listBidRequestAuditsByRequestId(id);
            model.addAttribute("audits", bidRequestService.listBidRequestAuditsByRequestId(id));

            //将这个申请人的风控材料查询并传递给前台

            UserFileQueryObject qo = new UserFileQueryObject();
            qo.setApplierId(userInfo.getId());
            qo.setState(UserFile.PASS); //只查询被审核通过的材料
            qo.setPageSize(0);

            model.addAttribute("userFiles", userFileService.listPassedFiles(qo));
        }

        return "bidrequest/borrow_info";

    }

}
