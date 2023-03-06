package com.junmooo.springbootdemo.entity.atical;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@TableName(value = "ARTICLE_TREE")
public class ArticleTree {
    @TableId(value = "ID")
    private String id;
    private String tree;
    private String ownerId;
    private String comment;
    private String deleteFlag;
    private Long timeCreated;
    private Long timeUpdated;
}
