package com.jyu.fire.controller;

import com.jyu.fire.service.UserService;
import com.jyu.fire.vo.Result;
import com.jyu.fire.vo.params.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    //用户列表
    @RequestMapping("listUser")
    public Result listUser(@RequestBody PageParams pageParams) {
        return userService.listUser(pageParams);
    }


}
