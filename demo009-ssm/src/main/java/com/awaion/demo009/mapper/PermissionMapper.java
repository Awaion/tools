package com.awaion.demo009.mapper;

import com.awaion.demo009.domain.Permission;
import com.awaion.demo009.query.QueryObject;

import java.util.List;

public interface PermissionMapper {
    void save(Permission entity);

    void delete(Long id);

    List<Permission> listAll();

    /**
     * 查询总数
     *
     * @param qo
     * @return
     */
    Long queryByConditionCount(QueryObject qo);

    /**
     * 查询结果集
     *
     * @param qo
     * @return
     */
    List<Permission> queryByCondition(QueryObject qo);

    /**
     * 根据员工的ID查询该员工拥有的权限有哪些
     *
     * @param employeeId
     * @return
     */
    List<Permission> selectByEmployeeId(Long employeeId);

    List<String> queryPermissionsByUserId(Long id);
}
