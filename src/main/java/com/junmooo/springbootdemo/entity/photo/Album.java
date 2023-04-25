package com.junmooo.springbootdemo.entity.photo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@TableName("PHOTO_ALBUMS")
@Builder
public class Album {
    @TableId(value = "ID")
    private int id;
    private String albumName;
    private String photoIds;
    private String albumTitle;
    private String albumSubTitle;
    private String albumDesc;
    private String remark;
    private String uploaderId;
    private String deleteFlag;
    private String hideFlag;
    private int sortOrder;
    private Long timeCreated;
}
