package com.awaion.demo010.business.domain;

import com.awaion.demo010.base.domain.BaseDomain;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
public class AccountFlow extends BaseDomain {

    private Long accountId;
    private Date actionTime;
    private BigDecimal amount;
    private BigDecimal usableAmount; //变化之后的可用余额
    private BigDecimal freezedAmount; //变化之后的冻结金额
    private int actionType;
    private String note;


}
