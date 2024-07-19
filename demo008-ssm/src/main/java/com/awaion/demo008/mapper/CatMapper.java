package com.awaion.demo008.mapper;

import com.awaion.demo008.domain.Cat;
import com.awaion.demo008.query.QueryObject;

import java.util.List;

public interface CatMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Cat record);

    Cat selectByPrimaryKey(Long id);

    List<Cat> selectAll();

    int updateByPrimaryKey(Cat record);

    Long queryPageCount(QueryObject qo);

    List<Cat> queryPageData(QueryObject qo);
}