package com.awaion.demo010.business.service;

import com.awaion.demo010.business.domain.Bid;

public interface IBidService {

    int insert(Bid record);

    Bid selectByPrimaryKey(Long id);

    int update(Bid record);


}
