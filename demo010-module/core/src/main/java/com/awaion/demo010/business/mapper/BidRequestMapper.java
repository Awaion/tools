package com.awaion.demo010.business.mapper;

import com.awaion.demo010.business.domain.BidRequest;
import com.awaion.demo010.business.query.BidRequestQueryObject;

import java.util.List;

public interface BidRequestMapper {

    int insert(BidRequest record);

    BidRequest selectByPrimaryKey(Long id);

    int updateByPrimaryKey(BidRequest record);

    Integer querycount(BidRequestQueryObject qo);

    List<BidRequest> querydata(BidRequestQueryObject qo);


}