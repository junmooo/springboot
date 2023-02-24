package com.junmooo.springbootdemo.entity.auth;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
@TableName("CAUTH_OPERATOR_INFO")
public class Operator {
    @TableId(value = "OPER_ID")
    private String operId;
    private String operPwd;
    private String operName;
    private String operEmail;
    private String phoneNo;
    private String operStatus;
    private String remark;
    private String deleteFlag;
    private Date timeCreated;
    private Date timeUpdated;

}
