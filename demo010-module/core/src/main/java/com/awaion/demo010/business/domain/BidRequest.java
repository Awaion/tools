package com.awaion.demo010.business.domain;

import com.alibaba.fastjson.JSON;
import com.awaion.demo010.base.domain.BaseDomain;
import com.awaion.demo010.base.domain.BidConsts;
import com.awaion.demo010.base.domain.Consts;
import com.awaion.demo010.base.domain.Logininfo;
import com.awaion.demo010.business.util.CalculatetUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BidRequest extends BaseDomain {
    private int version;
    private int returnType;//还款类型//每月本息+先息后本

    private int bidRequestType;  //投标类型 我们这里是信用标

    private int bidRequestState; //投标状态

    private BigDecimal bidRequestAmount; //投标金额

    private BigDecimal currentRate; //年化总利率

    private BigDecimal minBidAmount; //最小投标金额

    private int months2Return; //还款期限  3 6 9 12个月

    private int bidCount = 0; //投标次数/笔,也就是目前有多少个人参与了投标


    private BigDecimal totalRewardAmount; //总回报金额

    private BigDecimal currentSum = Consts.ZERO; //最小投标金额

    private String title;  //借款标题

    private String description; //借款描述

    private String note;  //审核备注

    private Date disableDate; //投标截止日

    private int disableDays; //投标时长

    private Date applyTime; //申请时间

    private Date publishTime; //发布时间  /发标时间


    private List<Bid> bids = new ArrayList<>();  //对应的投标记录
    private Logininfo createUser; //投标申请人


    public int getPresent() { //获取当前的投资进度
        return currentSum.divide(bidRequestAmount, BidConsts.DISPLAY_SCALE, BigDecimal.ROUND_HALF_UP).multiply(CalculatetUtil.ONE_HUNDRED).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
        /*return 78;*/
    }

    public BigDecimal getRemainAmount() {
        return bidRequestAmount.subtract(currentSum);
    }


    public String getReturnTypeDisplay() {
        return returnType == BidConsts.RETURN_TYPE_MONTH_INTEREST ? "按月到期"
                : "等额本息";
    }

    public String getJsonString() {
        Map<String, Object> json = new HashMap<>();
        json.put("id", id);
        json.put("username", this.createUser.getUsername());
        json.put("title", title);
        json.put("bidRequestAmount", bidRequestAmount);
        json.put("currentRate", currentRate);
        json.put("months2Return", months2Return);
        json.put("returnType", getReturnTypeDisplay());
        json.put("totalRewardAmount", totalRewardAmount);

        return JSON.toJSONString(json);
    }


    public String getBidRequestStateDisplay() {
        switch (this.bidRequestState) {
            case BidConsts.BIDREQUEST_STATE_PUBLISH_PENDING:
                return "待发布";
            case BidConsts.BIDREQUEST_STATE_BIDDING:
                return "招标中";
            case BidConsts.BIDREQUEST_STATE_UNDO:
                return "已撤销";
            case BidConsts.BIDREQUEST_STATE_BIDDING_OVERDUE:
                return "流标";
            case BidConsts.BIDREQUEST_STATE_APPROVE_PENDING_1:
                return "满标一审";
            case BidConsts.BIDREQUEST_STATE_APPROVE_PENDING_2:
                return "满标二审";
            case BidConsts.BIDREQUEST_STATE_REJECTED:
                return "满标审核被拒";
            case BidConsts.BIDREQUEST_STATE_PAYING_BACK:
                return "还款中";
            case BidConsts.BIDREQUEST_STATE_COMPLETE_PAY_BACK:
                return "完成";
            case BidConsts.BIDREQUEST_STATE_PAY_BACK_OVERDUE:
                return "逾期";
            case BidConsts.BIDREQUEST_STATE_PUBLISH_REFUSE:
                return "发标拒绝";
            default:
                return "";
        }
    }

}
