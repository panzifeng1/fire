package com.jyu.fire.service;

import com.jyu.fire.pojo.User;
import com.jyu.fire.vo.Result;
import com.jyu.fire.vo.params.PageParams;
import org.springframework.beans.factory.annotation.Autowired;

public interface UserService {

    /**
     * 分页查询用户列表
     * @param pageParams
     * @return
     */
    Result listUser(PageParams pageParams);

    User findUserById(Long id);

    User findUser(String username, String password);


    /**
     * 根据用户名查询用户
     * @param name
     * @return
     */
    User selectByName(String name);
}
