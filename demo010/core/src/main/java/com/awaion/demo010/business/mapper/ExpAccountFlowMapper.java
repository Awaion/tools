package com.awaion.demo010.business.mapper;

import com.awaion.demo010.business.domain.SystemAccountFlow;

import java.util.List;

public interface ExpAccountFlowMapper {

    int insert(SystemAccountFlow record);

    SystemAccountFlow selectByPrimaryKey(Long expaccountid);

    List<SystemAccountFlow> selectAll();

    int updateByPrimaryKey(SystemAccountFlow record);
}