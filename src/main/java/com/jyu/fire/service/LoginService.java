package com.jyu.fire.service;

import com.jyu.fire.pojo.LoginLog;
import com.jyu.fire.pojo.User;
import com.jyu.fire.vo.Result;
import com.jyu.fire.vo.params.LoginParam;

public interface LoginService {
    int addLog(LoginLog log);
}
