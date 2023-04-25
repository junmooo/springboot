package com.junmooo.springbootdemo.entity.photo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

@Data
@TableName("PHOTO")
@Builder
public class Photo {
    @TableId(value = "ID")
    private String id;
    private String name;
    private String url;
    private String originalName;
    private String remark;
    private String deleteFlag;
    private String hideFlag;
    private String width;
    private String height;
    private String uploaderId;
    private Long timeCreated;
    private int sortOrder;
    private int album;
}
