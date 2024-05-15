package com.awaion.demo010.business.mapper;

import com.awaion.demo010.business.domain.Bid;
import org.apache.ibatis.annotations.Param;

public interface BidMapper {

    int insert(Bid record);

    Bid selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Bid record);

    void updateStates(@Param("id") Long id, @Param("state") int state);


}