package com.awaion.demo010.base.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class IpLog extends BaseDomain {


    public static final int STATE_SUCCESS = 0;  //登录成功
    public static final int STATE_FAILED = 1;  //登录失败

    private String ip;  //登录的主机ip

    private Date loginTime; //登录时间

    private int state; //登录状态

    private int userType;  //用户类型

    private String username; //登录的用户名


    public String getUserTypeDisplay() {

        return userType == Logininfo.TYPE_CLIENT ? "前台用户" : "管理员";

    }

    public String getStateDisplay() {
        return state == STATE_SUCCESS ? "登录成功" : "登录失败";
    }

}
