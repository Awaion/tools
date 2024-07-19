package com.awaion.demo010.business.query;

import com.awaion.demo010.base.query.QueryObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BidRequestQueryObject extends QueryObject {

    private int bidRequestState = -1;

    private int[] states;

    private String orderBy;
    private String orderType;


}
