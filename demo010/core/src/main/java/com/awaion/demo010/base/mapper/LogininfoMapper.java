package com.awaion.demo010.base.mapper;

import com.awaion.demo010.base.domain.Logininfo;
import org.apache.ibatis.annotations.Param;

public interface LogininfoMapper {

    int insert(Logininfo record);

    Logininfo selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Logininfo record);

    int selectCountByUsername(String username);

    Logininfo checkByUsernameAndPwd(@Param("username") String username, @Param("password") String password, @Param("userType") int userType);


}