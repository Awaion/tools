package com.awaken.tool.demo004.test;

import com.awaken.tool.demo004.mapper.SysUserMapper;
import com.awaken.tool.demo004.model.Sys_User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles(value = "unit-test")
@SpringBootTest
public class MyBatisPlusTest {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<Sys_User> sysUserList = sysUserMapper.selectList(null);
        Assertions.assertEquals(5, sysUserList.size());
        sysUserList.forEach(System.out::println);
    }
}
