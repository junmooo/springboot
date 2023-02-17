package com.junmooo.springbootdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.junmooo.springbootdemo.common.constant.ErrorCode;
import com.junmooo.springbootdemo.entity.interview.InterviewQuestion;
import com.junmooo.springbootdemo.entity.vo.CommonResponse;
import com.junmooo.springbootdemo.service.interview.InterviewService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("interview")
public class InterviewQuestionController {

    @Autowired
    private InterviewService interviewService;

    @RequestMapping("addOrUpdateQuestion")
    public JSONObject addOrUpdateQuestion(@RequestBody InterviewQuestion question) {
        try {
            int i;
            if (question.getId() != null){
                i = interviewService.updataInterview(question);
            }else{
                i = interviewService.addInterview(question);
            }

            if (i >= 1) {
                return CommonResponse.success(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResponse.fail(ErrorCode.SYSERR, "添加失败 e");
        }
        return CommonResponse.fail(ErrorCode.SYSERR, "添加失败");
    }

    @RequestMapping("questions")
    public JSONObject questions(@RequestBody InterviewQuestion question) {
        try {
            List interviews = interviewService.interviews(question);
            return CommonResponse.success(interviews);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResponse.success("查询失败 e");
        }
    }

    @RequestMapping("deleteQuestion")
    public JSONObject deleteQuestion(@Param("id") String id) {
        try {
            int i = interviewService.deleteById(id);
            if (i == 1) {
                return CommonResponse.success(null);
            }
            return CommonResponse.fail(ErrorCode.SQLERR, "del fail");        } catch (Exception e) {
            e.printStackTrace();
            return CommonResponse.fail(ErrorCode.SQLERR, "del fail");        }
    }
}
