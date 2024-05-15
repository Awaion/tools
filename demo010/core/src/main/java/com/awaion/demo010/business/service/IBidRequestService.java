package com.awaion.demo010.business.service;

import com.awaion.demo010.base.query.PageResult;
import com.awaion.demo010.business.domain.BidRequest;
import com.awaion.demo010.business.domain.BidRequestAudit;
import com.awaion.demo010.business.query.BidRequestQueryObject;

import java.math.BigDecimal;
import java.util.List;

public interface IBidRequestService {

    int insert(BidRequest record);

    BidRequest selectByPrimaryKey(Long id);

    int update(BidRequest record);

    void apply(BidRequest bidRequest);

    PageResult advanQuery(BidRequestQueryObject qo);

    void publishAudit(int id, String remark, int state);

    List<BidRequestAudit> listBidRequestAuditsByRequestId(Long bidRequestId);

    List<BidRequest> list5items();

    void bid(Long bidRequestId, BigDecimal amount);

    void fullAudit1(int id, String remark, int state);

    void fullAudit2(int id, String remark, int state);


}
