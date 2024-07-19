package com.awaion.demo010.base.mapper;

import com.awaion.demo010.base.domain.Account;

public interface AccountMapper {

    int insert(Account record);

    Account selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Account record);
}