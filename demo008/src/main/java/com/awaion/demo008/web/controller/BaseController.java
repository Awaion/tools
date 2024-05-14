package com.awaion.demo008.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 用来处理错误信息
 */
@Controller
public class BaseController {

    @ResponseBody
    @ExceptionHandler(AuthorizationException.class)
    public void handleException(HandlerMethod method, HttpServletResponse response) throws IOException {

        //method表示当前请求的方法duixiang
        if (method != null) {
            ResponseBody rp = method.getMethodAnnotation(ResponseBody.class);  //获取方法上面贴的responsebody的标签
            if (rp == null) {
                //1,页面的普通请求没有权限--->跳转到nopermission.jsp页面
                response.sendRedirect("/nopermission.jsp");
            } else {
                response.setContentType("text/json;charset=utf-8");
                Map<String, Object> resultObj = new HashMap<>();

                resultObj.put("success", false);
                resultObj.put("msg", "您没有操作权限");
                resultObj.put("rows", Collections.EMPTY_LIST);
                response.getWriter().write(new ObjectMapper().writeValueAsString(resultObj).toString());
            }
        }
    }
}
