package com.awaion.demo008.service;

import com.awaion.demo008.domain.Permission;
import com.awaion.demo008.page.PageResult;
import com.awaion.demo008.query.QueryObject;

import java.util.List;

public interface IPermissionService {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    Permission selectByPrimaryKey(Long id);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);

    PageResult queryPagePage(QueryObject qo);

    void load();

    PageResult queryPageForRoleForm();

    PageResult selectPageForRoleFormByRoleId(Long roleId);

    List<String> queryStringByUserId(Long id);
}
