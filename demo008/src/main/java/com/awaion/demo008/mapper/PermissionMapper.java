package com.awaion.demo008.mapper;

import com.awaion.demo008.domain.Permission;
import com.awaion.demo008.query.QueryObject;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    Permission selectByPrimaryKey(Long id);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);

    Long queryPageCount(QueryObject qo);

    List<Permission> queryPageDataResult(QueryObject qo);

    List<Permission> selectPageForRoleFormByRoleId(Long roleId);

    List<String> queryStringByUserId(Long id);
}