package com.jyu.fire.config;

import com.jyu.fire.handler.CustomizeAuthenticationEntryPoint;
import com.jyu.fire.handler.CustomizeAuthenticationFailureHandler;
import com.jyu.fire.handler.CustomizeAuthenticationSuccessHandler;
import com.jyu.fire.handler.CustomizeLogoutSuccessHandler;
import com.jyu.fire.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //未登录时的跳转逻辑
    @Autowired
    CustomizeAuthenticationEntryPoint authenticationEntryPoint;

    //登录成功
    @Autowired
    CustomizeAuthenticationSuccessHandler authenticationSuccessHandler;

    //退出成功
    @Autowired
    CustomizeLogoutSuccessHandler logoutSuccessHandler;

    //登录失败
    @Autowired
    CustomizeAuthenticationFailureHandler authenticationFailureHandler;

    @Bean
    public UserDetailsService userDetailsService() {
        //获取账号密码及权限信息
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        //设置默认的加密方式
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //配置认证方式
        auth.userDetailsService(userDetailsService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http相关的配置，包括登入登出，异常处理，会话管理等
        http.authorizeRequests().
                antMatchers("/getUser").hasAuthority("query_user").
                //异常处理(权限拒绝、登录失效等)
                and().exceptionHandling().
                authenticationEntryPoint(authenticationEntryPoint).//匿名用户访问无权限资源时的异常处理
                //登入
                and().formLogin().
                permitAll().//允许所有用户
                successHandler(authenticationSuccessHandler).//登录成功处理逻辑
                failureHandler(authenticationFailureHandler).//登录失败处理逻辑
                //登出
                and().logout().
                permitAll().//允许所有用户
                logoutSuccessHandler(logoutSuccessHandler).//登出成功处理逻辑
                deleteCookies("JSESSIONID").//登出之后删除cookie
                and().csrf().disable();


    }
}
