package com.junmooo.springbootdemo.service.interview;

import com.junmooo.springbootdemo.entity.interview.InterviewQuestion;

import java.util.List;

public interface InterviewService {

    int addInterview(InterviewQuestion interview) throws Exception;

    List interviews(InterviewQuestion interview) throws Exception;

    int deleteById(String id) throws Exception;

    int updataInterview(InterviewQuestion question) throws Exception;
}
