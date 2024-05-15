package com.awaion.demo010.business.domain;

import com.awaion.demo010.base.domain.BaseDomain;
import com.awaion.demo010.base.domain.Logininfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Bid extends BaseDomain {

    private BigDecimal actualRate;  //实际年利率
    private BigDecimal availableAmount;  //投标金额

    private Long bidRequestId; //对应的借款标的id
    private String bidRequestTitle; //
    private Date bidTime; //投标时间
    private int bidRequestState; //标的状态

    private Logininfo bidUser;  //投标人


}
