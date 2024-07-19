package com.awaion.demo010.base.util;


import com.awaion.demo010.base.domain.IpLog;
import com.awaion.demo010.base.domain.Logininfo;
import com.awaion.demo010.base.domain.VerifyCodeVO;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

public class UserContext {

    public static String USER_IN_SESSION = "logininfo";
    public static String VERIFYCODEVO_IN_SESSION = "VERIFYCODEVO_IN_SESSION";
    public static String LOG_IN_SESSION = "lastLog";

    private static HttpSession getSession() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
    }

    //存储验证码
    public static void setVerifyCodeVO(VerifyCodeVO vo) {
        getSession().setAttribute(VERIFYCODEVO_IN_SESSION, vo);
    }


    //获取验证码
    public static VerifyCodeVO getVerifyCodeVO() {
        return (VerifyCodeVO) getSession().getAttribute(VERIFYCODEVO_IN_SESSION);
    }

    //将对象缓存到session中

    public static void setCurrentUser(Logininfo logininfo) {

        getSession().setAttribute(USER_IN_SESSION, logininfo);

    }

    //将上一次登录日志对象存到session里
    public static void setLastLog(IpLog lastLog) {
        getSession().setAttribute(LOG_IN_SESSION, lastLog);
    }


    //从session中获取存储logininfo

    public static Logininfo getCurrentUser() {
        return (Logininfo) getSession().getAttribute(USER_IN_SESSION);
    }


}
