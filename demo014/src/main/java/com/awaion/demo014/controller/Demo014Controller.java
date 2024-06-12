package com.awaion.demo014.controller;

import com.awaion.demo014.domain.LoginInfo;
import com.awaion.demo014.mapper.LoginInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Demo014Controller {

    @Autowired
    public LoginInfoMapper mapper;

    @GetMapping("/get")
    public LoginInfo getById() {
        return mapper.getLoginInfoById(1L);
    }
}
