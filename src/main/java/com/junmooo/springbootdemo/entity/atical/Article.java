package com.junmooo.springbootdemo.entity.atical;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@TableName(value = "ARTICLE")
public class Article {
    @TableId(value = "ID")
    private String id;
    private String title;
    private String article;
    private String type;
    private String comment;
    private String authorId;
    private String authorName;
    private String deleteFlag;
    private Long timeCreated;
    private Long timeUpdated;
}
