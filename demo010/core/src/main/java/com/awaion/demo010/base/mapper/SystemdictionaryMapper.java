package com.awaion.demo010.base.mapper;

import com.awaion.demo010.base.domain.SystemDictionary;
import com.awaion.demo010.base.query.SystemDictionaryQueryObject;

import java.util.List;

public interface SystemdictionaryMapper {

    int insert(SystemDictionary record);

    SystemDictionary selectByPrimaryKey(Long id);

    List<SystemDictionary> selectAll();

    int updateByPrimaryKey(SystemDictionary record);

    int queryCount(SystemDictionaryQueryObject qo);

    List<SystemDictionary> queryData(SystemDictionaryQueryObject qo);


}