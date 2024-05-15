package com.awaion.demo010.business.domain;

import com.awaion.demo010.base.domain.BaseDomain;
import com.awaion.demo010.base.domain.BidConsts;
import com.awaion.demo010.base.domain.Logininfo;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
public class PaymentScheduleDetail extends BaseDomain {

    private BigDecimal bidAmount; //这个投标人总共的投标金额
    private Long bidId; //投资人投的标的id

    private BigDecimal totalAmount = BidConsts.ZERO; //本期还款总额
    private BigDecimal principal = BidConsts.ZERO;  //本期还款的本金
    private BigDecimal interest = BidConsts.ZERO; //本期还款利息

    private int monthIndex; //本次还款是第几期
    private Date deadLine; //本期还款截止时间
    private Long bidRequestId;//对应的借款对象
    private Date payDate; //用户的实际还款时间????
    private int returnType; //还款方式,等同借款

    private Long paymentScheduleId; //对应的还款计划

    private Logininfo fromLogininfo; //还款人(发标)  //关联
    private Long toLogininfoId; //收款人id(投标人)


}
