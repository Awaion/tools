package com.awaion.demo008.util;

import com.awaion.demo008.domain.Employee;
import com.awaion.demo008.domain.SystemMenu;
import com.awaion.demo008.service.ISystemMenuService;
import lombok.Setter;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ExtendFormAuthenticationFilter extends FormAuthenticationFilter {
    @Setter
    ISystemMenuService systemMenuService;


    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
//        this.issueSuccessRedirect(request, response);

        System.out.println("登录成功");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        //*********************************************************
        //当用户登录成功之后查询该用户拥有哪些菜单,并把该用户的菜单集合放入到session中
        List<SystemMenu> allmenu = systemMenuService.indexMenu();
        //根据当前用户获取该用户的菜单ID集合
        Employee currentUser = (Employee) subject.getPrincipal();

        if (!currentUser.isAdmin()) { //如果不是超级管理员
            List<Long> userMenuIds = systemMenuService.queryIdsByUserId(currentUser.getId());
            //遍历比较完整菜单项是否在用户菜单Id集合中
            MenuUtil.filterAllMenu(allmenu, userMenuIds);

        }
        Session session = subject.getSession();
        //把处理后的菜单保存到session中
        session.setAttribute(MenuUtil.USERMENU, allmenu);

        //*********************************************************

        //给前台响应json对象
        System.out.println("登陆成功");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("{\"success\":true,\"msg\":\"登入成功\"}");
        out.flush();
        out.close();

        return false;  //是否需要执行下一个过滤器,如果没有,则执行上一次记录的地址/login
    }

    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        try {
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            String message = e.getClass().getSimpleName();
            if ("IncorrectCredentialsException".equals(message)) {
                out.println("{\"success\":false,\"msg\":\"密码错误\"}");
            } else if ("UnknownAccountException".equals(message)) {
                out.println("{\"success\":false,\"msg\":\"账号不存在\"}");
            } else if ("LockedAccountException".equals(message)) {
                out.println("{\"success\":false,\"msg\":\"账号被锁定\"}");
            } else if ("AuthenticationException".equals(message)) {
                out.println("{\"success\":false,\"msg\":\"认证失败\"}");
            } else {
                out.println("{\"success\":false,\"msg\":\"未知错误\"}");
            }
            out.flush();
            out.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return false;
    }

}
