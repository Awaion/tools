package com.awaion.demo010.business.mapper;

import com.awaion.demo010.business.domain.ExpAccount;

import java.util.List;

public interface ExpAccountMapper {

    int insert(ExpAccount record);

    ExpAccount selectByPrimaryKey(Long id);

    List<ExpAccount> selectAll();

    int updateByPrimaryKey(ExpAccount record);
}