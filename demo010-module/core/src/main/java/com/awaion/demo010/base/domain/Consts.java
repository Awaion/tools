package com.awaion.demo010.base.domain;

import java.math.BigDecimal;

public class Consts {

    public static BigDecimal ZERO = new BigDecimal("0.0000");

    //发送验证码的规定时间间隔
    public static int SEND_VERIFYCODE_INTERVAL = 5;

    //验证码有效时间
    public static int VERIFYCODE_VALID_TIMELIMIT = 600;  //10分钟

    //验证邮件有效时间
    public static int VERIFYEMAIL_VAILID_DAY = 5;  //5天


    //信用认证最低分数

    public static int CREDIT_BORROW_SCORE_LIMIT = 30;

    //用户注册的时候的体验金金额

    public static BigDecimal EXPACCOUNT_INIT_USABLE_AMOUNT = new BigDecimal("500.0000");


}
