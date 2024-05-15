package com.awaion.demo010.business.service;

import com.awaion.demo010.business.domain.ExpAccount;

import java.util.List;

public interface IExpAccountService {

    int insert(ExpAccount record);

    ExpAccount selectByPrimaryKey(Long id);

    List<ExpAccount> selectAll();

    int updateByPrimaryKey(ExpAccount record);

}
