package com.awaion.demo010.base.service.impl;

import com.awaion.demo010.base.domain.IpLog;
import com.awaion.demo010.base.mapper.IplogMapper;
import com.awaion.demo010.base.query.IpLogQueryObject;
import com.awaion.demo010.base.query.PageResult;
import com.awaion.demo010.base.service.IIpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class IpLogServiceImpl implements IIpLogService {

    @Autowired
    IplogMapper iplogMapper;


    @Override
    public PageResult advanQuery(IpLogQueryObject qo) {

        //查询总的记录条数
        Integer count = iplogMapper.querycount(qo);
        if (count == 0) {
            return new PageResult(Collections.EMPTY_LIST, 0, 1, qo.getPageSize());
        }
        //查询所有的对象形成的集合
        List<IpLog> data = iplogMapper.querydata(qo);

        return new PageResult(data, count, qo.getCurrentPage(), qo.getPageSize());
    }
}
