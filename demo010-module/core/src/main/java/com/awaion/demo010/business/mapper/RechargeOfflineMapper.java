package com.awaion.demo010.business.mapper;

import com.awaion.demo010.business.domain.RechargeOffline;
import com.awaion.demo010.business.query.RechargeOfflineQueryObject;

import java.util.List;

public interface RechargeOfflineMapper {

    int insert(RechargeOffline record);

    RechargeOffline selectByPrimaryKey(Long id);


    int updateByPrimaryKey(RechargeOffline record);


    //高级查询对应方法
    Integer querycount(RechargeOfflineQueryObject qo);

    List<RechargeOffline> querydata(RechargeOfflineQueryObject qo);
}