package com.awaion.demo009.service;

import com.awaion.demo009.domain.Employee;
import com.awaion.demo009.page.PageResult;
import com.awaion.demo009.query.EmployeeQueryObject;

import java.util.List;

public interface IEmployeeService {
    void save(Employee entity);

    void update(Employee entity);

    void delete(Long id);

    Employee get(Long id);

    List<Employee> listAll();

    /**
     * 根据前天的条件进行高级查询+分页
     *
     * @param qo
     * @return
     */
    PageResult queryByCondition(EmployeeQueryObject qo);

    /**
     * 登录方法
     *
     * @param username
     * @param password
     */
    void login(String username, String password);

    Employee queryByUsername(String username);
}
