package com.awaion.demo010.business.domain;

import com.awaion.demo010.base.domain.BaseDomain;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;


@Setter
@Getter
public class SystemAccountFlow extends BaseDomain {
    private Date actionTime;
    private int actionType;
    private BigDecimal amount;
    private String note;
    private BigDecimal usableAmount;
    private BigDecimal freezedAmount;

}
