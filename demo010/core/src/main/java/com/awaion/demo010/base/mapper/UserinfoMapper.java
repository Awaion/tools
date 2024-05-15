package com.awaion.demo010.base.mapper;

import com.awaion.demo010.base.domain.UserInfo;

public interface UserinfoMapper {

    int insert(UserInfo record);

    UserInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKey(UserInfo record);
}