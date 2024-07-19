package com.awaken.tool.demo004.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("`sys_user`")
public class Sys_User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
