package com.junmooo.springbootdemo.service.user;

import com.junmooo.springbootdemo.entity.user.User;

public interface UserService {
    User getUserByName(String name) throws Exception;

    User add(User user) throws Exception;
}
