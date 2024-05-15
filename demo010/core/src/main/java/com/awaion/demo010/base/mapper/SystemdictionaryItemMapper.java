package com.awaion.demo010.base.mapper;

import com.awaion.demo010.base.domain.SystemDictionaryItem;
import com.awaion.demo010.base.query.SystemDictionaryQueryObject;

import java.util.List;

public interface SystemdictionaryItemMapper {


    int deleteByPrimaryKey(Long id);

    int insert(SystemDictionaryItem record);

    SystemDictionaryItem selectByPrimaryKey(Long id);

    List<SystemDictionaryItem> selectAll();

    int updateByPrimaryKey(SystemDictionaryItem record);

    int queryCount(SystemDictionaryQueryObject qo);

    List<SystemDictionaryItem> queryData(SystemDictionaryQueryObject qo);

    List<SystemDictionaryItem> listItemsBySn(String sn);
}