package com.awaion.demo008.service;

import com.awaion.demo008.domain.Role;
import com.awaion.demo008.page.PageResult;
import com.awaion.demo008.query.QueryObject;

import java.util.ArrayList;
import java.util.List;

public interface IRoleService {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    PageResult queryPage(QueryObject qo);

    List<Long> queryRoleIdListForEmployeeForm(Long employeeId);

    void addMenu(ArrayList<Long> ids, Long roleId);

    List<String> queryStringByUserId(Long id);
}
