package com.awaion.demo010.base.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Account extends BaseDomain {

    private int version;
    private String tradePassword;  //交易密码
    private BigDecimal usableAmount = BidConsts.ZERO; //可用余额
    private BigDecimal freezedAmount = BidConsts.ZERO; //冻结金额
    private BigDecimal unReceiveInterest = BidConsts.ZERO; //待收利息
    private BigDecimal unReceivePrincipal = BidConsts.ZERO; //待收本金
    private BigDecimal unReturnAmount = BidConsts.ZERO; //待还本息
    private BigDecimal remainBorrowLimit = BidConsts.INIT_BORROWLIMIT; //剩余额度
    private BigDecimal borrowLimit = BidConsts.INIT_BORROWLIMIT; //总额度


    public BigDecimal getTotalAmount() {
        return usableAmount.add(freezedAmount).add(unReceiveInterest).add(unReceivePrincipal);
    }

    ;


}
