package com.awaion.demo008.service.impl;

import com.awaion.demo008.domain.Employee;
import com.awaion.demo008.domain.Role;
import com.awaion.demo008.mapper.EmployeeMapper;
import com.awaion.demo008.page.PageResult;
import com.awaion.demo008.query.EmployeeQueryObject;
import com.awaion.demo008.service.IEmployeeService;
import com.awaion.demo008.service.ISystemMenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private ISystemMenuService systemMenuService;

    public int deleteByPrimaryKey(Long id) {
        return employeeMapper.deleteByPrimaryKey(id);
    }

    public int insert(Employee record) {
        //需要给用户默认的密码,默认的状态,默认是普通用户,设置入职时间
        record.setState(Employee.NORMAL);
        record.setAdmin(false);
        record.setInputtime(new Date());
        Md5Hash md5 = new Md5Hash("1", record.getUsername(), 1);
        record.setPassword(md5.toString());
        int count = employeeMapper.insert(record);
        //处理员工和角色的中间表关系
        for (Role role : record.getRoles()) {
            employeeMapper.insertRelation(role.getId(), record.getId());
        }
        return count;
    }

    public Employee selectByPrimaryKey(Long id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    public List<Employee> selectAll() {
        return employeeMapper.selectAll();
    }

    public int updateByPrimaryKey(Employee record) {
        int count = employeeMapper.updateByPrimaryKey(record);
        //删除之前所有的关系
        employeeMapper.deleteRelation(record.getId());
        //处理员工和角色的中间表关系
        for (Role role : record.getRoles()) {
            employeeMapper.insertRelation(role.getId(), record.getId());
        }
        return count;
    }

    public PageResult queryPage(EmployeeQueryObject qo) {

        //根据角色控制数据的显示的核心操作
        Subject subject = SecurityUtils.getSubject();
        if (!subject.hasRole("HR")) {
            //如果没有hr的角色
            Employee currentUser = (Employee) subject.getPrincipal();
            qo.setCurrentUserId(currentUser.getId());
        }
        //根据角色控制数据的显示的核心操作

        //符合条件的总数
        Long count = employeeMapper.queryPageCount(qo);
        if (count <= 0) {
            return new PageResult(0L, Collections.EMPTY_LIST);
        }
        List<Employee> result = employeeMapper.queryPageData(qo);
        return new PageResult(count, result);
    }

    public void quit(Long id) {
        employeeMapper.changeState(id, Employee.LEAVE);
    }

    @Override
    public Employee queryEmpByUsername(String username) {
        return employeeMapper.queryEmpByUsername(username);
    }

}
