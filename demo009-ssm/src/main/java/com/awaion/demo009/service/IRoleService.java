package com.awaion.demo009.service;

import com.awaion.demo009.domain.Role;
import com.awaion.demo009.page.PageResult;
import com.awaion.demo009.query.QueryObject;

import java.util.List;

public interface IRoleService {
    void save(Role entity);

    void update(Role entity);

    void delete(Long id);

    Role get(Long id);

    List<Role> listAll();

    /**
     * 根据前天的条件进行高级查询+分页
     *
     * @param qo
     * @return
     */
    PageResult queryByCondition(QueryObject qo);

    List<String> queryRolesByUserId(Long id);
}
