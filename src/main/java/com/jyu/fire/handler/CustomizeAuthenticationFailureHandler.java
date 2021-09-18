package com.jyu.fire.handler;

import com.alibaba.fastjson.JSON;
import com.jyu.fire.vo.ErrorCode;
import com.jyu.fire.vo.Result;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.security.auth.login.AccountExpiredException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class CustomizeAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        Result result = null;
        if (e instanceof BadCredentialsException) {
            //账号密码错误
            result = Result.fail(ErrorCode.FAIL_SELECT.getCode(),ErrorCode.FAIL_SELECT.getMsg());
        }

        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(result));

    }
}
