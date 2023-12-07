package com.junmooo.springbootdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.junmooo.springbootdemo.service.auth.OperService;
import com.junmooo.springbootdemo.utils.RedisUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/hello")
@CrossOrigin(origins = "*")
public class HelloController {

    @RequestMapping("hi/{var}")
    public JSONObject Hello(@Param("data") String data, @PathVariable String var) {
        JSONObject res = new JSONObject();
        String key1="test:hash";
        RedisUtils.hset(key1,"name","walker");
        RedisUtils.hset(key1,"age","18");
        RedisUtils.hset(key1,"school","广药");
        logger.info(RedisUtils.hget(key1, "name"));
        logger.info(RedisUtils.hget(key1, "age"));
        logger.info(RedisUtils.hget(key1, "school"));
        res.put("code", 200);
        res.put("msg", "success");
        int sleep = new Random().nextInt(200);
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        res.put("data",var);
        return res;
    }
}
