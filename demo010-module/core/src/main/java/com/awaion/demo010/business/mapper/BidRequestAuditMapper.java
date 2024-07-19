package com.awaion.demo010.business.mapper;

import com.awaion.demo010.business.domain.BidRequestAudit;
import com.awaion.demo010.business.query.BidRequestAuditQueryObject;

import java.util.List;

public interface BidRequestAuditMapper {

    int insert(BidRequestAudit record);

    BidRequestAudit selectByPrimaryKey(Long id);


    //高级查询对应方法
    Integer querycount(BidRequestAuditQueryObject qo);

    List<BidRequestAudit> querydata(BidRequestAuditQueryObject qo);


    List<BidRequestAudit> listBidRequestAuditsByRequestId(Long bidRequestId);


}