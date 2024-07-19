package com.awaion.demo010.base.service;

import com.awaion.demo010.base.domain.Logininfo;

public interface ILogininfoService {

    Logininfo register(String username, String password);

    int insert(Logininfo record);

    Logininfo selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Logininfo record);

    boolean checkUsername(String username);

    Logininfo login(String username, String password, String ip, int userType);
}
