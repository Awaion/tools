package com.awaion.demo008.mapper;

import com.awaion.demo008.domain.Employee;
import com.awaion.demo008.domain.Permission;
import com.awaion.demo008.query.EmployeeQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

    Employee login(@Param("username") String username, @Param("password") String password);

    Long queryPageCount(EmployeeQueryObject qo);

    List<Employee> queryPageData(EmployeeQueryObject qo);

    void changeState(@Param("employeeId") Long id, @Param("employeeState") int employeeStateQuit);

    void insertRelation(@Param("roleId") Long roleId, @Param("employeeId") Long employeeId);

    List<Permission> queryPermissionByEmployeeId(Long employeeId);

    void deleteRelation(Long id);

    Employee queryEmpByUsername(String username);
}