package com.awaion.demo010.base.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VerifyEmailVO {

    private long userId;

    private String email;

    private Date sendTime; //发送时间

    private String uuid;  //对应的验证码

}
