package com.awaion.demo008.service;

import com.awaion.demo008.domain.SystemMenu;
import com.awaion.demo008.page.PageResult;
import com.awaion.demo008.query.QueryObject;

import java.util.List;

public interface ISystemMenuService {
    int deleteByPrimaryKey(Long id);

    int insert(SystemMenu record);

    SystemMenu selectByPrimaryKey(Long id);

    List<SystemMenu> selectAll();

    int updateByPrimaryKey(SystemMenu record);

    PageResult queryPage(QueryObject qo);

    List<SystemMenu> queryTree();

    List<SystemMenu> queryForRole();

    List<Long> queryMenuIdsListForRole(Long roleId);

    List<SystemMenu> indexMenu();

    List<Long> queryIdsByUserId(Long id);
}
