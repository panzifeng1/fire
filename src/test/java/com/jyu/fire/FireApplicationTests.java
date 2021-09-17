package com.jyu.fire;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jyu.fire.mapper.DeviceMapper;
import com.jyu.fire.mapper.UserMapper;
import com.jyu.fire.pojo.Device;
import com.jyu.fire.pojo.User;
import com.jyu.fire.service.LoginService;
import com.jyu.fire.vo.Result;
import com.jyu.fire.vo.params.LoginParam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
class FireApplicationTests {

    @Autowired
    LoginService service;

    @Autowired
    UserMapper userMapper;

    @Autowired
    DeviceMapper deviceMapper;





    @Test
    public void testUpdate() {
        Device device = new Device();
        device.setNum("1001");
        device.setName("李四");
        device.setType(12);
        device.setStatus(1);
        deviceMapper.update(device,null);
    }



}
