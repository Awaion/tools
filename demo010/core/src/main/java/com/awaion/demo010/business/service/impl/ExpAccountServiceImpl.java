package com.awaion.demo010.business.service.impl;

import com.awaion.demo010.business.domain.ExpAccount;
import com.awaion.demo010.business.mapper.ExpAccountMapper;
import com.awaion.demo010.business.service.IExpAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpAccountServiceImpl implements IExpAccountService {

    @Autowired
    private ExpAccountMapper expAccountMapper;

    @Override
    public int insert(ExpAccount record) {
        return expAccountMapper.insert(record);
    }

    @Override
    public ExpAccount selectByPrimaryKey(Long id) {
        return expAccountMapper.selectByPrimaryKey(id);
    }


    @Override
    public List<ExpAccount> selectAll() {
        return expAccountMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(ExpAccount record) {//todo : i am here

        int ret = expAccountMapper.updateByPrimaryKey(record);
        if (ret <= 0) {
            throw new RuntimeException("乐观锁失败,体验金账户" + record);
        }
        return ret;
    }
}
