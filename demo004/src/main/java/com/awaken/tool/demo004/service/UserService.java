package com.awaken.tool.demo004.service;

import com.awaken.tool.demo004.mapper.SysUserMapper;
import com.awaken.tool.demo004.model.Sys_User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserService service;
    @Autowired
    private SysUserMapper mapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = RuntimeException.class)
    public void add1() {
        Sys_User user = new Sys_User();
        user.setAge(18);
        user.setName("名字1");
        mapper.insert(user);
        service.add2();

        throw new RuntimeException("故意抛异常");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = RuntimeException.class)
    public void add2() {
        Sys_User user = new Sys_User();
        user.setAge(18);
        user.setName("名字2");
        mapper.insert(user);
    }
}
