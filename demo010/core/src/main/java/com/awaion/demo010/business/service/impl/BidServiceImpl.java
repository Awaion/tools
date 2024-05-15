package com.awaion.demo010.business.service.impl;

import com.awaion.demo010.business.domain.Bid;
import com.awaion.demo010.business.mapper.BidMapper;
import com.awaion.demo010.business.service.IBidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BidServiceImpl implements IBidService {

    @Autowired
    private BidMapper bidMapper;


    @Override
    public int insert(Bid record) {
        return bidMapper.insert(record);
    }

    @Override
    public Bid selectByPrimaryKey(Long id) {
        return bidMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(Bid record) {
        return bidMapper.updateByPrimaryKey(record);
    }
}
