package com.junmooo.springbootdemo.entity.auth;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

@Data
@TableName("CAUTH_ROLE_INFO")
@Builder
public class Role {
    @TableId
    private String roleId;
    private String roleName;
    private String remark;
    private String deleteFlag;
    private Long timeCreated;
    private Long timeUpdated;
    private String roleCode;
}
