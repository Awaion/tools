package com.awaion.demo009.mapper;

import com.awaion.demo009.domain.Role;
import com.awaion.demo009.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    void save(Role entity);

    void update(Role entity);

    void delete(Long id);

    Role get(Long id);

    List<Role> listAll();

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
    List<Role> queryByCondition(QueryObject qo);

    /**
     * 处理中间表的关系
     *
     * @param roleId
     * @param permissionId
     */
    void insertRelation(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

    /**
     * 删除中间表的关系
     *
     * @param roleId
     */
    void deleteRelation(Long roleId);

    List<String> queryRolesByUserId(Long id);

}
