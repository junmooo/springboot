package com.junmooo.springbootdemo.entity.interview;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
@Data
@TableName("INTERVIEW_QUESTION")
public class InterviewQuestion {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String question;
    private String answer;
    private String options;
    private String correct;
    private String explanation;
    // 00 java 01 web
    private String type;
    private String comment;
}
