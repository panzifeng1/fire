package com.jyu.fire.service.impl;

import com.jyu.fire.pojo.User;
import com.jyu.fire.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("执行自定义登录逻辑");
        if (username == null || "".equals(username)) {
            throw new RuntimeException("用户不能为空");
        }
        User user = userService.selectByName(username);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }

        GrantedAuthority authority = null;
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (user.getUsername() == "admin") {
            authority  = new SimpleGrantedAuthority("admin");
            grantedAuthorities.add(authority);
        } else {
            authority = new SimpleGrantedAuthority("user");
            grantedAuthorities.add(authority);
        }
        if (user.getStatus() == 1) {
            authority = new SimpleGrantedAuthority("enable");
            grantedAuthorities.add(authority);
        } else {
            authority = new SimpleGrantedAuthority("disable");
            grantedAuthorities.add(authority);
        }

        if (!"admin".equals(username)) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        //该密码从数据库中查询到加密后的
        String password = passwordEncoder.encode("123");
        return new org.springframework.security.core.userdetails.User(username,password,
                grantedAuthorities);
    }
}
