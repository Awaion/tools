package com.awaion.demo010.base.service;

import com.awaion.demo010.base.domain.Account;

public interface IAccountService {

    int save(Account record);

    Account selectByPrimaryKey(Long id);

    void updateByPrimaryKey(Account record);


}
