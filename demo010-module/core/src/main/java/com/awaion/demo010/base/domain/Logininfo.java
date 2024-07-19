package com.awaion.demo010.base.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Logininfo extends BaseDomain {

    public static int STATE_NORMAL = 0;
    public static int STATE_LOCK = 1;

    public static int TYPE_CLIENT = 0;
    public static int TYPE_MANAGER = 1;


    private String username;
    private String password;

    private int state;
    private int userType;  //用户类型:前台用户+后台用户


}
