package com.junmooo.springbootdemo.entity.user;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
@TableName("USER")
public class User {

    @TableId(value = "ID")
    private String id;
    private String pwd;
    private String name;
    private String email;
    private String phoneNo;
    private String status;
    private String remark;
    private String deleteFlag;
    private Long timeCreated;
    private Long timeUpdated;
    private String avatar;
}
