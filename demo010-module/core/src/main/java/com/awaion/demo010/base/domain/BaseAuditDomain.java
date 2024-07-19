package com.awaion.demo010.base.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BaseAuditDomain extends BaseDomain {  //审核相关的基础类

    public static final int NORMAL = 0;
    public static final int PASS = 1;
    public static final int REJECT = 2;


    protected int state;

    protected Logininfo applier; //认证申请人

    protected Date applyTime; //申请时间

    protected Logininfo auditor;  //审核人

    protected Date auditTime; //审核时间

    protected String remark; //审核备注

    public String getStateDisplay() {
        switch (state) {
            case NORMAL:
                return "待审核";
            case PASS:
                return "审核通过";
            case REJECT:
                return "审核拒绝";
            default:
                return "";
        }
    }


}
