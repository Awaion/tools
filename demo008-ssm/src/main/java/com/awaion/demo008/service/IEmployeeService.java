package com.awaion.demo008.service;

import com.awaion.demo008.domain.Employee;
import com.awaion.demo008.page.PageResult;
import com.awaion.demo008.query.EmployeeQueryObject;

import java.util.List;

public interface IEmployeeService {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

    PageResult queryPage(EmployeeQueryObject qo);

    void quit(Long id);

    Employee queryEmpByUsername(String username);
}
