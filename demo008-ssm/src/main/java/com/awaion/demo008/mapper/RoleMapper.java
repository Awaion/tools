package com.awaion.demo008.mapper;

import com.awaion.demo008.domain.Role;
import com.awaion.demo008.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    Long queryPageCount(QueryObject qo);

    List<Role> queryPageDataResult(QueryObject qo);

    void insertRelation(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

    void deleteRelation(Long roleId);

    List<Long> queryRoleIdListForEmployeeForm(Long employeeId);

    void insertMeneRelation(@Param("menuId") Long menuId, @Param("roleId") Long roleId);

    void deleteMenuRelation(Long roleId);

    List<String> queryStringByUserId(Long id);
}