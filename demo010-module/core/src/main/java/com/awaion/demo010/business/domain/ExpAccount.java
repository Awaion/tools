package com.awaion.demo010.business.domain;

import com.awaion.demo010.base.domain.BaseDomain;
import com.awaion.demo010.base.domain.BidConsts;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ExpAccount extends BaseDomain {

    private int version;
    private BigDecimal usableAmount = BidConsts.ZERO;  //体验金账户余额

    private BigDecimal freezedAmount = BidConsts.ZERO;//体验经冻结金额
    private BigDecimal unReturnExpAmount = BidConsts.ZERO;//临时垫収体验金


}
