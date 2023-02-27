package com.junmooo.springbootdemo.service.message;

import com.junmooo.springbootdemo.entity.message.Message;
import com.junmooo.springbootdemo.entity.token.UserToken;

import java.util.List;

public interface MessageService {

    int add(Message message) throws Exception;

    List messages(Message message, UserToken userToken) throws Exception;

    int deleteById(String id) throws Exception;

    int update(Message message) throws Exception;
}
