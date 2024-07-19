package com.awaion.demo010.base.service;

import com.awaion.demo010.base.domain.UserInfo;

public interface IUserinfoService {

    int save(UserInfo record);

    UserInfo selectByPrimaryKey(Long id);

    void updateByPrimaryKey(UserInfo record);

    void bindPhone(String phoneNumber, String verifyCode);

    boolean bindEmail(String key);

    void updateBasicInfo(UserInfo userInfo);
}
