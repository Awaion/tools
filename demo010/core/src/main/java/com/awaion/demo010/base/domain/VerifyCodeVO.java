package com.awaion.demo010.base.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
public class VerifyCodeVO {
    private String phoneNumber;

    private Date sendTime; //发送时间

    private String verifyCode;  //对应的验证码


}
