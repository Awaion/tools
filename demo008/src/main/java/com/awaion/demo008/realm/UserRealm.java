package com.awaion.demo008.realm;

import com.awaion.demo008.domain.Employee;
import com.awaion.demo008.domain.Role;
import com.awaion.demo008.service.IEmployeeService;
import com.awaion.demo008.service.IPermissionService;
import com.awaion.demo008.service.IRoleService;
import lombok.Setter;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserRealm extends AuthorizingRealm {

    public String getName() {
        return "userRealm";
    }

    @Setter
    private IEmployeeService employeeService;

    @Setter
    private IRoleService roleService;

    @Setter
    private IPermissionService permissionService;

    //登录认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();

        //根据用户名查询整个用户对象
        Employee employee = employeeService.queryEmpByUsername(username);
        //如果结果为nul,则返回null
        if (employee == null) {
            return null;
        }
        //创建认证对象并返回
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(employee, employee.getPassword(), getName());
        return info;
    }

    //授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //获取当前登录的用户
        Employee currentUser = (Employee) principalCollection.getPrimaryPrincipal();

        List<String> roles = null;
        List<String> perms = null;
        if (currentUser.isAdmin()) {  //判断是超级管理员
            //拥有所有的权限
            perms = new ArrayList<String>(Arrays.asList("*:*"));
            //从数据库中获取所有的角色集合,然后将sn注入到roles中
            roles = new ArrayList<>();
            List<Role> roleList = roleService.selectAll();

            for (Role role : roleList) {
                roles.add(role.getSn());
            }

        } else { //不是超级管理员
            roles = roleService.queryStringByUserId(currentUser.getId());
            perms = permissionService.queryStringByUserId(currentUser.getId());

        }
        //创建授权对象
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //关联角色和权限集合
        info.addRoles(roles);
        info.addStringPermissions(perms);
        System.out.println("进行了授权操作...");

        return info;
    }

}
