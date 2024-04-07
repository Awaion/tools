package com.awaken.tool.demo002.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@Schema(description = "用户消息推送方式")
public class UserPushTypeEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "推送方式,1-短信;2-邮件;3-app;4-wechat")
    private Integer pushType;

    @Schema(description = "通知推送接收地址")
    private String receiveAddress;

    @Schema(description = "是否启用,0-未启用,1-启用")
    private Integer enable;

}

