package com.awaion.demo010.base.mapper;

import com.awaion.demo010.base.domain.IpLog;
import com.awaion.demo010.base.query.IpLogQueryObject;

import java.util.List;

public interface IplogMapper {

    int insert(IpLog ipLog);

    Integer querycount(IpLogQueryObject qo);

    List<IpLog> querydata(IpLogQueryObject qo);

    //根据用户名获取上一次登录的登录对象
    List<IpLog> getLastIpLog(String username);

}