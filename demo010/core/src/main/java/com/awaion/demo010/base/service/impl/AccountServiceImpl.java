package com.awaion.demo010.base.service.impl;

import com.awaion.demo010.base.domain.Account;
import com.awaion.demo010.base.mapper.AccountMapper;
import com.awaion.demo010.base.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public int save(Account record) {
        int count = accountMapper.insert(record);
        return count;
    }

    @Override
    public Account selectByPrimaryKey(Long id) {
        return accountMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateByPrimaryKey(Account record) {

        int count = accountMapper.updateByPrimaryKey(record);
        if (count <= 0) {
            throw new RuntimeException("乐观锁失败,account" + record.getId());  //写到这里,差id
        }

    }
}
