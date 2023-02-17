package com.junmooo.springbootdemo.entity;

import lombok.Data;

@Data
public class User{
    private long userId;
    private String username;
    private String passwd;
    private String email;
    private String createTime;
    private int del;
    private String remark;
    private String additional;
}
