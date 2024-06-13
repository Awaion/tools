package com.awaion.demo016.service;

import com.awaion.demo016.properties.Demo016Properties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Demo016Service {
    @Autowired
    private Demo016Properties properties;

    public String getInfo() {
        return "loginName:" + properties.getLoginName() + ",password:" + properties.getPassword();
    }
}
