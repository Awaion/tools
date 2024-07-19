package com.awaion.demo008.service;

import com.awaion.demo008.domain.Cat;
import com.awaion.demo008.page.PageResult;
import com.awaion.demo008.query.QueryObject;

import java.util.List;

public interface ICatService {
    int deleteByPrimaryKey(Long id);

    int insert(Cat record);

    Cat selectByPrimaryKey(Long id);

    List<Cat> selectAll();

    int updateByPrimaryKey(Cat record);

    PageResult queryPage(QueryObject qo);
}
