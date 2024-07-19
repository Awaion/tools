package com.awaion.demo009.realm;

import com.awaion.demo009.domain.Employee;
import com.awaion.demo009.service.IEmployeeService;
import com.awaion.demo009.service.IPermissionService;
import com.awaion.demo009.service.IRoleService;
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
    @Setter
    private IEmployeeService employeeService;
    @Setter
    private IRoleService roleService;
    @Setter
    private IPermissionService permissionService;


    public String getName() {
        return "UserRealm";
    }

    /*认证的方法*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();  //获取令牌中的username信息
        //根据用户名查询用户employee对象
        Employee currentUser = employeeService.queryByUsername(username);
        System.out.println(currentUser);

        if (currentUser == null) {  //这里模拟数据库中只有zhangsan这一个用户
            //用户提交的令牌在数据库中没有找到对应的数据
            return null;
        }
        //返回认证信息对象
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(currentUser, currentUser.getPassword(), getName());
        return info;
    }


    /*授权的方法*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        Employee employee = (Employee) principals.getPrimaryPrincipal();
        System.out.println(employee);
        //从数据库中获取权限信息
        //判断是否是超级管理员
        List<String> roles = null;
        List<String> perms = null;
        if (employee.isAdmin()) {
            roles = new ArrayList<>(Arrays.asList("admin"));
            perms = new ArrayList<>(Arrays.asList("*:*"));
        } else {
            roles = roleService.queryRolesByUserId(employee.getId());
            perms = permissionService.queryPermissionsByUserId(employee.getId());
        }

        //*************************************
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roles);
        info.addStringPermissions(perms);
        return info;
    }

}
