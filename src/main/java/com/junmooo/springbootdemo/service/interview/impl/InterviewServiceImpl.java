package com.junmooo.springbootdemo.service.interview.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.junmooo.springbootdemo.entity.interview.InterviewQuestion;
import com.junmooo.springbootdemo.mapper.interview.InterviewMapper;
import com.junmooo.springbootdemo.service.interview.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewServiceImpl implements InterviewService {

    @Autowired
    private InterviewMapper interviewMapper;

    @Override
    public int addInterview(InterviewQuestion interview) {
        int insert = interviewMapper.insert(interview);
        return insert;
    }

    @Override
    public List interviews(InterviewQuestion interview) {
        QueryWrapper wrapper = new QueryWrapper(interview);
        return interviewMapper.selectList(wrapper);
    }

    @Override
    public int deleteById(String id) {
        return interviewMapper.deleteById(id);
    }

    @Override
    public int updataInterview(InterviewQuestion question) {
        return interviewMapper.updateById(question);
    }
}
