package com.junmooo.springbootdemo.service.message.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.junmooo.springbootdemo.entity.message.Message;
import com.junmooo.springbootdemo.entity.token.UserToken;
import com.junmooo.springbootdemo.mapper.message.MessageMapper;
import com.junmooo.springbootdemo.service.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public int add(Message message) {
        int insert = messageMapper.insert(message);
        return insert;
    }

    @Override
    public List messages(Message message, UserToken userToken) {
        QueryWrapper wrapper = new QueryWrapper(message);
        return messageMapper.selectList(wrapper);
    }

    @Override
    public int deleteById(String id) {
        return messageMapper.deleteById(id);
    }

    @Override
    public int update(Message message) {
        return messageMapper.updateById(message);
    }
}
