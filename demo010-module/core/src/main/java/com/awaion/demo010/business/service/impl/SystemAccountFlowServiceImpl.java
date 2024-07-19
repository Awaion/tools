package com.awaion.demo010.business.service.impl;

import com.awaion.demo010.business.domain.SystemAccountFlow;
import com.awaion.demo010.business.mapper.SystemAccountFlowMapper;
import com.awaion.demo010.business.service.ISytemAccountFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemAccountFlowServiceImpl implements ISytemAccountFlowService {

    @Autowired
    private SystemAccountFlowMapper systemAccountFlowMapper;

    @Override
    public int insert(SystemAccountFlow record) {
        return systemAccountFlowMapper.insert(record);
    }
}
