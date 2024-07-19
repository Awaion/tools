package com.awaion.demo014.domain;

import lombok.Data;

@Data
public class LoginInfo {
    private Long id;
    private String loginName;
    private String password;
}
