package com.awaion.demo010.business.domain;

import com.awaion.demo010.base.domain.BaseAuditDomain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BidRequestAudit extends BaseAuditDomain {


    public static final int TYPE_PUBLISH = 0;
    public static final int TYPE_FULL1 = 1;
    public static final int TYPE_FULL2 = 2;

    private Long bidRequestId; //对应的借款标id
    private int auditType;  //审核类型


    public String getAuditTypeDisplay() {
        switch (auditType) {
            case TYPE_PUBLISH:
                return "发标前审核";
            case TYPE_FULL1:
                return "满标一审";
            case TYPE_FULL2:
                return "满标二审";
            default:
                return "";
        }
    }
}
