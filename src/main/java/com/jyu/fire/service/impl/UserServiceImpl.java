package com.jyu.fire.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jyu.fire.mapper.UserMapper;
import com.jyu.fire.pojo.User;
import com.jyu.fire.service.LoginService;
import com.jyu.fire.service.UserService;
import com.jyu.fire.vo.ErrorCode;
import com.jyu.fire.vo.LoginUserVo;
import com.jyu.fire.vo.Result;
import com.jyu.fire.vo.params.PageParams;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LoginService loginService;

    /**
     * 分页查询user数据库表
     * @param pageParams
     * @return
     */
    @Override
    public Result listUser(PageParams pageParams) {
        Page<User> page = new Page<>(pageParams.getPage(),pageParams.getPageSize());
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        //按照是否启用进行排序
        queryWrapper.orderByDesc(User::getStatus);
        //按照创建时间排序
        queryWrapper.orderByDesc(User::getCreate_time);
        Page<User> userPage = userMapper.selectPage(page, queryWrapper);
        List<User> records = userPage.getRecords();
        List<LoginUserVo> userVoList = copyList(records);

        return Result.success(userVoList);
    }

    private List<LoginUserVo> copyList(List<User> records) {
        List<LoginUserVo> list = new ArrayList<>();
        for (User record : records) {
            list.add(copy(record));
        }
        return list;
    }

    private LoginUserVo copy(User user) {
        LoginUserVo loginUserVo = new LoginUserVo();
        BeanUtils.copyProperties(user,loginUserVo);
        return loginUserVo;
    }

    @Override
    public User findUserById(Long id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            user = new User();
            user.setUsername("");
        }
        return user;
    }

    @Override
    public User findUser(String username, String password) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,username);
        queryWrapper.eq(User::getPassword,password);
        queryWrapper.select(User::getUsername,User::getStatus);
        queryWrapper.last("limit 1");
        return userMapper.selectOne(queryWrapper);
    }


    @Override
    public User selectByName(String name) {
        return userMapper.selectByName(name);
    }


}
