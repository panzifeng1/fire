package com.jyu.fire;

import com.jyu.fire.mapper.LoginLogMapper;
import com.jyu.fire.pojo.LoginLog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoginLogTest {

    @Autowired
    private LoginLogMapper logMapper;

    @Test
    public void testAdd() {
        LoginLog loginLog = new LoginLog();
        loginLog.setUser_id(1L);
        loginLog.setIp("127.0.0.1");
        loginLog.setUser_agent("ios");
        int insert = logMapper.insert(loginLog);
        System.out.println(insert);
    }
}
