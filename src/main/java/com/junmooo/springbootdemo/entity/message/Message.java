package com.junmooo.springbootdemo.entity.message;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
@Data
@TableName("MESSAGE")
@Builder
public class Message {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String messageContent;
    private String style;
    private String authorId;
    private String type;
    private String comment;
    private Long createdTime;
    private Long updatedTime;
    private String authorName;
}
