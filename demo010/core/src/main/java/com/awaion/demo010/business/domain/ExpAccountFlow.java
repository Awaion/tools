package com.awaion.demo010.business.domain;

import com.awaion.demo010.base.domain.BaseDomain;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
public class ExpAccountFlow extends BaseDomain {

    private Long expAccountId; //体验金账户
    private Date actionTime; //交易时间
    private BigDecimal amount; //交易金额
    private int actionType; //交易类型
    private String note;//说明

    private BigDecimal usableAmount; //交易之后体验金账户余额
    private BigDecimal freezedAmount; //交易之后体验金账户冻结金额


}
