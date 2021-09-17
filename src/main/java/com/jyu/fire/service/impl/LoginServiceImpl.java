package com.jyu.fire.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.jyu.fire.mapper.LoginLogMapper;
import com.jyu.fire.pojo.LoginLog;
import com.jyu.fire.pojo.User;
import com.jyu.fire.service.LoginService;
import com.jyu.fire.service.UserService;
import com.jyu.fire.vo.ErrorCode;
import com.jyu.fire.vo.Result;
import com.jyu.fire.vo.params.LoginParam;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginLogMapper logMapper;

    @Override
    public int addLog(LoginLog log) {
        return logMapper.insert(log);
    }
}
