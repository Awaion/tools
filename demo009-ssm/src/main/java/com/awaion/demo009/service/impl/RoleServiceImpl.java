package com.awaion.demo009.service.impl;

import com.awaion.demo009.domain.Permission;
import com.awaion.demo009.domain.Role;
import com.awaion.demo009.mapper.RoleMapper;
import com.awaion.demo009.page.PageResult;
import com.awaion.demo009.query.QueryObject;
import com.awaion.demo009.service.IRoleService;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

public class RoleServiceImpl implements IRoleService {
    @Setter
    private RoleMapper roleMapper;

    @Override
    public void save(Role entity) {
        roleMapper.save(entity);
        //处理中间表关系   角色ID   权限ID
        for (Permission p : entity.getPermissions()) {
            roleMapper.insertRelation(entity.getId(), p.getId());
        }

    }

    @Override
    public void update(Role entity) {
        roleMapper.update(entity);
        //删除中间表关系
        roleMapper.deleteRelation(entity.getId());
        //新增中间表关系
        for (Permission p : entity.getPermissions()) {
            roleMapper.insertRelation(entity.getId(), p.getId());
        }
    }

    @Override
    public void delete(Long id) {
        roleMapper.delete(id);
    }

    @Override
    public Role get(Long id) {
        return roleMapper.get(id);
    }

    @Override
    public List<Role> listAll() {
        return roleMapper.listAll();
    }

    @Override
    public PageResult queryByCondition(QueryObject qo) {
        //查询符合条件的总记录数
        Long count = roleMapper.queryByConditionCount(qo);
        //如果记录数==0,没有结果,直接返回
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST, 1, qo.getPageSize());
        }
        //查询结果集
        List<Role> result = roleMapper.queryByCondition(qo);
        return new PageResult(count.intValue(), result, qo.getCurrentPage(), qo.getPageSize());
    }

    @Override
    public List<String> queryRolesByUserId(Long id) {
        return roleMapper.queryRolesByUserId(id);
    }

}
