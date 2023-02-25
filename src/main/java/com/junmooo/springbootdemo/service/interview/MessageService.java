package com.junmooo.springbootdemo.service.interview;

import com.junmooo.springbootdemo.entity.message.Message;
import com.junmooo.springbootdemo.entity.token.OperToken;

import java.util.List;

public interface MessageService {

    int add(Message message) throws Exception;

    List messages(Message message, OperToken operToken) throws Exception;

    int deleteById(String id) throws Exception;

    int update(Message message) throws Exception;
}
