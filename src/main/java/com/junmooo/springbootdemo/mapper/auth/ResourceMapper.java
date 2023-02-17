package com.junmooo.springbootdemo.mapper.auth;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.junmooo.springbootdemo.entity.auth.Resource;
import org.apache.ibatis.annotations.Delete;

public interface ResourceMapper extends BaseMapper<Resource> {

    @Delete("WITH RECURSIVE CTE (RESOURCE_ID, PARENT_ID) AS (" +
            "  SELECT     RESOURCE_ID," +
            "             PARENT_ID" +
            "  FROM       HELLO.CAUTH_RESOURCE_DEF" +
            "  WHERE      PARENT_ID = #{parentId}" +
            "  UNION ALL" +
            "  SELECT     P.RESOURCE_ID," +
            "             P.PARENT_ID" +
            "  FROM       HELLO.CAUTH_RESOURCE_DEF P" +
            "  INNER JOIN CTE" +
            "          ON P.PARENT_ID = CTE.RESOURCE_ID" +
            ")" +
            "" +
            "DELETE FROM HELLO.CAUTH_RESOURCE_DEF WHERE RESOURCE_ID IN (SELECT RESOURCE_ID FROM CTE) OR RESOURCE_ID = #{parentId};")
    int deleteWithRecursiveById(String parentId);
}
