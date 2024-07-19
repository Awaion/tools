package com.awaion.demo009.web.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        //判断session是否有登录的账号.
        Object currentUser = invocation.getInvocationContext().getSession().get("user_in_session");

        if (currentUser == null) {
            //没有登录
            return "login";
        }
        return invocation.invoke();
    }

}
