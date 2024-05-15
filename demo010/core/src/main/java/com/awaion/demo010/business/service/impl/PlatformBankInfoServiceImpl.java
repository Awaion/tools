package com.awaion.demo010.business.service.impl;

import com.awaion.demo010.base.query.PageResult;
import com.awaion.demo010.base.query.PlatFormBankInfoQueryObject;
import com.awaion.demo010.business.domain.PlatFormBankInfo;
import com.awaion.demo010.business.mapper.PlatFormBankInfoMapper;
import com.awaion.demo010.business.service.IPlatformBankInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PlatformBankInfoServiceImpl implements IPlatformBankInfoService {

    @Autowired
    private PlatFormBankInfoMapper platFormBankInfoMapper;

    @Override
    public int insert(PlatFormBankInfo record) {
        return platFormBankInfoMapper.insert(record);
    }

    @Override
    public PlatFormBankInfo selectByPrimaryKey(Long id) {
        return platFormBankInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PlatFormBankInfo> selectAll() {
        return platFormBankInfoMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(PlatFormBankInfo bk) {

        if (bk.getId() == null) {  //id为null,是保存操作
            return platFormBankInfoMapper.insert(bk);

        } else {  //id不为null,是更新操作
            return platFormBankInfoMapper.updateByPrimaryKey(bk);
        }
    }

    @Override
    public PageResult advanQuery(PlatFormBankInfoQueryObject qo) {
        //查询总的记录条数
        Integer count = platFormBankInfoMapper.querycount(qo);
        if (count == 0) {
            return new PageResult(Collections.EMPTY_LIST, 0, 1, qo.getPageSize());
        }
        //查询所有的对象形成的集合
        List<PlatFormBankInfo> data = platFormBankInfoMapper.querydata(qo);

        return new PageResult(data, count, qo.getCurrentPage(), qo.getPageSize());

    }
}
