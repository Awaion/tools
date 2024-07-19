package com.awaion.demo008.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@ObjectProp("系统日志")
public class SystemLog {
    private Long id;

    @ObjectProp("操作人")
    private Employee opUser;

    @ObjectProp("操作IP")
    private String opIP;

    @ObjectProp("操作方法")
    private String functio;

    @ObjectProp("参数")
    private String params;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ObjectProp("操作时间")
    private Date opTime;


}
