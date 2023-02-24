package com.junmooo.springbootdemo.entity.file;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

@Data
@TableName("FILE_ENTITY")
@Builder
public class FileEntity {
    @TableId(value = "ID")
    private String id;
    private String fileName;
    private String url;
    private String originalFileName;
    private String remark;
    private String deleteFlag;
    private String isImage;
    private String uploaderId;
    private Long timeCreated;

}
