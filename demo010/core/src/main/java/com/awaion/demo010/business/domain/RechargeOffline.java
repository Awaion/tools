package com.awaion.demo010.business.domain;

import com.alibaba.fastjson.JSON;
import com.awaion.demo010.base.domain.BaseAuditDomain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RechargeOffline extends BaseAuditDomain {

    private PlatFormBankInfo bankInfo;
    private String tradeCode;

    /*@DateTimeFormat(pattern = "yyyy-MM-dd")*/
    private String rechargeTime;

    private BigDecimal amount; //充值金额
    private String note;//审核意见


    public String getJsonString() {

        Map<String, Object> map = new HashMap<>();

        map.put("id", id);
        map.put("username", applier.getUsername());
        map.put("tradeCode", tradeCode);
        map.put("amount", amount);
        map.put("rechargeTime", rechargeTime);

        return JSON.toJSONString(map);
    }

}
