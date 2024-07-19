package com.awaion.demo010.business.service;

import com.awaion.demo010.base.query.PageResult;
import com.awaion.demo010.business.domain.BidRequestAudit;
import com.awaion.demo010.business.query.BidRequestAuditQueryObject;

public interface IBidRequestAuditService {

    int insert(BidRequestAudit record);

    BidRequestAudit selectByPrimaryKey(Long id);

    PageResult advanQuery(BidRequestAuditQueryObject qo);


}
