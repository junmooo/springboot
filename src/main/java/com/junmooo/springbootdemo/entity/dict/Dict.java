package com.junmooo.springbootdemo.entity.dict;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
@TableName("DICT")
public class Dict {
    @TableId(value = "ID")
    private Long id;
    private String label;
    private String val;
    private String comment;
    private String remark;
    private String deleteFlag;
    private Long timeCreated;
    private Long timeUpdated;
}
