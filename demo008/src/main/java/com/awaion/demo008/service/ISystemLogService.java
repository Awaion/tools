package com.awaion.demo008.service;

import com.awaion.demo008.domain.SystemLog;
import com.awaion.demo008.page.PageResult;
import com.awaion.demo008.query.QueryObject;

import java.util.List;

public interface ISystemLogService {
    int deleteByPrimaryKey(Long id);

    int insert(SystemLog record);

    SystemLog selectByPrimaryKey(Long id);

    List<SystemLog> selectAll();

    int updateByPrimaryKey(SystemLog record);

    PageResult queryPage(QueryObject qo);
}
