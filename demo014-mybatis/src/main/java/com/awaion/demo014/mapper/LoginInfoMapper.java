package com.awaion.demo014.mapper;


import com.awaion.demo014.domain.LoginInfo;
import org.apache.ibatis.annotations.Param;

public interface LoginInfoMapper {
    LoginInfo getLoginInfoById(@Param("id") Long id);
}
