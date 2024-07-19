package com.awaion.demo010.business.domain;

import com.awaion.demo010.base.domain.BaseDomain;
import com.awaion.demo010.base.domain.BidConsts;
import com.awaion.demo010.base.domain.Consts;
import com.awaion.demo010.base.domain.Logininfo;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class PaymentSchedule extends BaseDomain {

    private Long bidRequestId;//对应的借款对象
    private String bidRequestTitle;//借款名称

    private Logininfo borrowUser;//借款人,也就是未来的还款人  //关联

    private Date deadLine; //本期还款截止时间
    private Date payDate; //用户的实际还款时间????

    private BigDecimal totalAmount = Consts.ZERO; //本期还款的总金额
    private BigDecimal principal = Consts.ZERO;  //本期还款的本金
    private BigDecimal interest = Consts.ZERO; //本期还款利息

    private int monthIndex; //本次还款是第几期
    private int state = BidConsts.PAYMENT_STATE_NORMAL; //正常还款状态

    private int bidRequestType; //借款类型
    private int returnType; //还款方式,等同借款

    //本期还款计划对应的还款计划明细//done
    private List<PaymentScheduleDetail> paymentScheduleDetails = new ArrayList<PaymentScheduleDetail>();

    public String getStateDisplay() {
        switch (state) {
            case BidConsts.PAYMENT_STATE_NORMAL:
                return "正常待还";
            case BidConsts.PAYMENT_STATE_DONE:
                return "已还";
            case BidConsts.PAYMENT_STATE_OVERDUE:
                return "逾期";
            default:
                return "";
        }
    }
}
