package com.awaion.demo009.web.interceptor;

import com.awaion.demo009.domain.Employee;
import com.awaion.demo009.util.PermissionUtil;
import com.awaion.demo009.util.RequiredPermission;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.lang.reflect.Method;
import java.util.Set;

public class SecurityInterceptor extends AbstractInterceptor {
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        Employee currentUser = (Employee) invocation.getInvocationContext().getSession().get("user_in_session");
        //该登录用户是超级管理员
        if (currentUser.isAdmin()) {
            return invocation.invoke();
        }
        Object action = invocation.getAction();//Action对象
        String methodName = invocation.getProxy().getMethod();//方法
        Method method = action.getClass().getMethod(methodName);
        //判断请求方法有没有贴@RequiredPermission标签
        RequiredPermission rp = method.getAnnotation(RequiredPermission.class);
        if (rp == null) {
            //该方法没有贴注解,不需要受权限控制
            return invocation.invoke();
        }
        //从session中获取用户的权限集合,
        Set<String> permissionSet = (Set<String>) invocation.getInvocationContext().getSession().get("permission_in_session");
        //判断当前请求方法对应的权限表达式是否在用户的权限集合中.
        String expression = PermissionUtil.buildExpression(method);
        if (permissionSet.contains(expression)) {
            //如果在,该用户有访问该资源的权限,放行
            return invocation.invoke();
        }
        //如果不在在,该用户没有有访问该资源的权限,进行拦截
        return "nopermission";
    }

}
