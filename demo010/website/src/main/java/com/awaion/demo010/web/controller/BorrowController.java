package com.awaion.demo010.web.controller;

import com.awaion.demo010.base.domain.*;
import com.awaion.demo010.base.query.UserFileQueryObject;
import com.awaion.demo010.base.service.IAccountService;
import com.awaion.demo010.base.service.IRealAuthService;
import com.awaion.demo010.base.service.IUserFileService;
import com.awaion.demo010.base.service.IUserinfoService;
import com.awaion.demo010.base.util.UserContext;
import com.awaion.demo010.business.domain.BidRequest;
import com.awaion.demo010.business.domain.BidRequestAudit;
import com.awaion.demo010.business.service.IBidRequestService;
import com.awaion.demo010.page.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class BorrowController {

    @Autowired
    IAccountService accountService;

    @Autowired
    IUserinfoService userinfoService;

    @Autowired
    IUserFileService userFileService;

    @Autowired
    IRealAuthService realAuthService;


    @Autowired
    IBidRequestService bidRequestService;


    @RequestMapping("/borrow.do")
    public String toborrow(Model model) {
        Logininfo currentUser = UserContext.getCurrentUser();
        Long id = null;
        if (currentUser != null) {
            id = currentUser.getId();
        }
        model.addAttribute("creditBorrowScore", Consts.CREDIT_BORROW_SCORE_LIMIT);
        model.addAttribute("account", accountService.selectByPrimaryKey(id));
        model.addAttribute("userinfo", userinfoService.selectByPrimaryKey(id));

        if (currentUser != null) {

            return "borrow";  //已经登录,进入动态页面
        } else {
            return "redirect:/borrow.html";  //没登录,进入静态页面
        }
    }

    @RequestMapping("/borrowPage")
    public String borrowPage(Model model) {

        //得到当前用户userinfo
        UserInfo currentUserinfo = userinfoService.selectByPrimaryKey(UserContext.getCurrentUser().getId());
        //判断当前用户是否能发起付款  4个条件

        if (currentUserinfo.getIsRealAuth() && currentUserinfo.getIsVedioAuth() && currentUserinfo.getIsBasicInfo()
                && currentUserinfo.getScore() >= Consts.CREDIT_BORROW_SCORE_LIMIT
        ) {//如果能
            if (currentUserinfo.getHasBidRequestInProcess()) {//如果有一个借款在审核流程当中,跳转到borrow_apply_result.ftl页面
                return "borrow_apply_result";
            } else {//没有,则跳转到申请页面
                model.addAttribute("account", accountService.selectByPrimaryKey(currentUserinfo.getId()));
                model.addAttribute("minBidRequestAmount", BidConsts.SMALLEST_BIDREQUEST_AMOUNT);  //最小借款金额
                model.addAttribute("minBidAmount", BidConsts.SMALLEST_BID_AMOUNT);  //最小投标金额
                return "borrow_apply";
            }
        } else {
            return "redirect:/borrow.do";//如果不能,跳转会借款首页
        }
    }


    @RequestMapping("/borrow_apply")
    public String borrowApply(BidRequest bidRequest) {
        bidRequestService.apply(bidRequest);
        return "redirect:/borrowPage.do";

    }


    @RequestMapping("/borrow_info")
    public String borrowDetail(Long id, Model model) {

        BidRequest bidRequest = bidRequestService.selectByPrimaryKey(id);

        if (bidRequest != null) {
            //传递信息给前台
            UserInfo userInfo = userinfoService.selectByPrimaryKey(bidRequest.getCreateUser().getId());//获取借款人信息
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


            //当前session中的用户,也就是实际操作者
            Logininfo currentUser = UserContext.getCurrentUser();

            if (currentUser.getId().equals(userInfo.getId())) {  //借款人自己在浏览
                model.addAttribute("self", true);
            } else {
                model.addAttribute("self", false);
                model.addAttribute("account", accountService.selectByPrimaryKey(currentUser.getId()));
            }
        }
        return "borrow_info";
    }

    @RequestMapping("borrow_bid")
    @ResponseBody
    public AjaxResult bid(Long bidRequestId, BigDecimal amount) {
        AjaxResult result = new AjaxResult();
        try {
            bidRequestService.bid(bidRequestId, amount);
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("操作失败:" + e.getMessage());
        }
        return result;
    }





  /*  @RequestMapping("borrow_bid")
    @ResponseBody
    public AjaxResult bid(Long bidRequestId, BigDecimal amount){
        AjaxResult result=new AjaxResult();
        try{
            this.bidRequestService.bid(bidRequestId,amount);
            result.setSuccess(true);
        }catch(Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }*/


}
