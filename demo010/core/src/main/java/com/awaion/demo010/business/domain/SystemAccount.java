package com.awaion.demo010.business.domain;

import com.awaion.demo010.base.domain.BaseDomain;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class SystemAccount extends BaseDomain {

    private int version;

    private BigDecimal usableAmount;

    private BigDecimal freezedAmount;


}
