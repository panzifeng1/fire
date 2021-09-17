package com.jyu.fire.handler;

import com.alibaba.fastjson.JSON;
import com.jyu.fire.pojo.LoginLog;
import com.jyu.fire.service.LoginService;
import com.jyu.fire.service.UserService;
import com.jyu.fire.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    UserService userService;

    @Autowired
    LoginService loginService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //登录成功后将数据插入到login_log表中
        User userDetail = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        com.jyu.fire.pojo.User user = userService.selectByName(userDetail.getUsername());
        Long id = user.getId();
        LoginLog loginLog = new LoginLog();
        loginLog.setUser_id(id);
        //获取用户id，useragent


        loginService.addLog(loginLog);
        //返回json数据
        Result success = Result.success(null);
        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(success));
    }
}
