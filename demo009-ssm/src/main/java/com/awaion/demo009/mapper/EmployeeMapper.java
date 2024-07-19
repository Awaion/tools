package com.awaion.demo009.mapper;

import com.awaion.demo009.domain.Employee;
import com.awaion.demo009.query.EmployeeQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    void save(Employee entity);

    void update(Employee entity);

    void delete(Long id);

    Employee get(Long id);

    List<Employee> listAll();

    /**
     * 查询总数
     */
    Long queryByConditionCount(EmployeeQueryObject qo);

    /**
     * 查询结果集
     */
    List<Employee> queryByCondition(EmployeeQueryObject qo);

    /**
     * 插入中间表的关系
     */
    void insertRelation(@Param("roleId") Long roleId, @Param("employeeId") Long employeeId);

    /**
     * 删除中间表的关系
     */
    void deleteRelation(Long employeeId);

    /**
     * 登录方法
     *
     * @param username
     * @param password
     */
    Employee login(@Param("username") String username, @Param("password") String password);

    Employee queryByUsername(String username);
}
