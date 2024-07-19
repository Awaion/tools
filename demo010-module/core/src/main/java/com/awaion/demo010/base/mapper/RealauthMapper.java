package com.awaion.demo010.base.mapper;

import com.awaion.demo010.base.domain.RealAuth;
import com.awaion.demo010.base.query.RealAuthQueryObject;

import java.util.List;

public interface RealauthMapper {

    int insert(RealAuth record);

    RealAuth selectByPrimaryKey(Long id);

    int updateByPrimaryKey(RealAuth record);


    //高级查询
    Integer querycount(RealAuthQueryObject qo);

    List<RealAuth> querydata(RealAuthQueryObject qo);
}