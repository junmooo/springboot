package com.junmooo.springbootdemo.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.junmooo.springbootdemo.entity.user.User;
import com.junmooo.springbootdemo.mapper.user.UserMapper;
import com.junmooo.springbootdemo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserByName(String name) throws Exception {
        User user = User.builder().name(name).build();
        QueryWrapper qw = new QueryWrapper<>(user);
        return userMapper.selectOne(qw);
    }

    @Override
    public User add(User user) throws Exception {
        user.setId(UUID.randomUUID().toString());
        user.setTimeCreated(System.currentTimeMillis());
        if (userMapper.insert(user) == 1) {
            return user;
        }
        throw new Exception("user insert failed");
    }
}
