package com.awaion.demo008.mapper;

import com.awaion.demo008.domain.SystemLog;
import com.awaion.demo008.query.QueryObject;

import java.util.List;

public interface SystemLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemLog record);

    SystemLog selectByPrimaryKey(Long id);

    List<SystemLog> selectAll();

    int updateByPrimaryKey(SystemLog record);

    Long queryPageCount(QueryObject qo);

    List<SystemLog> queryPageData(QueryObject qo);
}