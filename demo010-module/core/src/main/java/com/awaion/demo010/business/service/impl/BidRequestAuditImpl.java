package com.awaion.demo010.business.service.impl;

import com.awaion.demo010.base.query.PageResult;
import com.awaion.demo010.business.domain.BidRequestAudit;
import com.awaion.demo010.business.mapper.BidRequestAuditMapper;
import com.awaion.demo010.business.query.BidRequestAuditQueryObject;
import com.awaion.demo010.business.service.IBidRequestAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class BidRequestAuditImpl implements IBidRequestAuditService {

    @Autowired
    private BidRequestAuditMapper bidRequestAuditMapper;

    @Override
    public int insert(BidRequestAudit record) {
        return bidRequestAuditMapper.insert(record);
    }

    @Override
    public BidRequestAudit selectByPrimaryKey(Long id) {
        return bidRequestAuditMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult advanQuery(BidRequestAuditQueryObject qo) {
        //查询总的记录条数
        Integer count = bidRequestAuditMapper.querycount(qo);
        if (count == 0) {
            return new PageResult(Collections.EMPTY_LIST, 0, 1, qo.getPageSize());
        }
        //查询所有的对象形成的集合
        List<BidRequestAudit> data = bidRequestAuditMapper.querydata(qo);

        return new PageResult(data, count, qo.getCurrentPage(), qo.getPageSize());
    }
}
