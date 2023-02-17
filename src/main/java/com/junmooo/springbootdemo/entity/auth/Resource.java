package com.junmooo.springbootdemo.entity.auth;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("CAUTH_RESOURCE_DEF")
public class Resource {
    @TableId
    private String resourceId;
    private String resourceName;
    private String parentId;
    private String resourcePath;
    private String menuFlag;
    private String hasChild;
    private int sortNum;
    private String resourceCode;
    private String resourceUrl;
    private String remark;
    private String deleteFlag;
    private Long timeCreated;
    private Long timeUpdated;

}
