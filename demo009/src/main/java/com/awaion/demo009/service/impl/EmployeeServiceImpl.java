package com.awaion.demo009.service.impl;

import com.awaion.demo009.domain.Employee;
import com.awaion.demo009.domain.Permission;
import com.awaion.demo009.domain.Role;
import com.awaion.demo009.mapper.EmployeeMapper;
import com.awaion.demo009.page.PageResult;
import com.awaion.demo009.query.EmployeeQueryObject;
import com.awaion.demo009.service.IEmployeeService;
import com.awaion.demo009.service.IPermissionService;
import lombok.Setter;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.struts2.ServletActionContext;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EmployeeServiceImpl implements IEmployeeService {
    @Setter
    private EmployeeMapper employeeMapper;
    @Setter
    private IPermissionService permissionService;

    @Override
    public void save(Employee entity) {
        String password = new Md5Hash(entity.getPassword(), entity.getName(), 2).toString();
        entity.setPassword(password);

        employeeMapper.save(entity);
        //处理中间表关系
        for (Role role : entity.getRoles()) {
            employeeMapper.insertRelation(role.getId(), entity.getId());
        }
    }

    @Override
    public void update(Employee entity) {
        employeeMapper.update(entity);
        //删除中间表关系
        employeeMapper.deleteRelation(entity.getId());
        //处理中间表关系
        for (Role role : entity.getRoles()) {
            employeeMapper.insertRelation(role.getId(), entity.getId());
        }
    }

    @Override
    public void delete(Long id) {
        employeeMapper.delete(id);
    }

    @Override
    public Employee get(Long id) {
        return employeeMapper.get(id);
    }

    @Override
    public List<Employee> listAll() {
        return employeeMapper.listAll();
    }

    @Override
    public PageResult queryByCondition(EmployeeQueryObject qo) {
        //查询符合条件的总记录数
        Long count = employeeMapper.queryByConditionCount(qo);
        //如果记录数==0,没有结果,直接返回
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST, 1, qo.getPageSize());
        }
        //查询结果集
        List<Employee> result = employeeMapper.queryByCondition(qo);
        return new PageResult(count.intValue(), result, qo.getCurrentPage(), qo.getPageSize());
    }

    @Override
    public void login(String username, String password) {
        Employee currentUser = employeeMapper.login(username, password);
        if (currentUser == null) {
            throw new RuntimeException("账号密码有误");
        }
        //当前对象保存到session.
        ServletActionContext.getRequest().getSession().setAttribute("user_in_session", currentUser);
        //把该用户的权限信息查询出来,并放入到session中.
        List<Permission> permissions = permissionService.selectByEmployeeId(currentUser.getId());
        Set<String> permissionSet = new HashSet<String>();
        for (Permission p : permissions) {
            permissionSet.add(p.getExpression());
        }
        ServletActionContext.getRequest().getSession().setAttribute("permission_in_session", permissionSet);

    }

    @Override
    public Employee queryByUsername(String username) {
        return employeeMapper.queryByUsername(username);
    }

}
