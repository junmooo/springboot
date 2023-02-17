package com.junmooo.springbootdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.junmooo.springbootdemo.service.auth.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("users/{var}")
    public JSONObject Hello(@Param("data") String data, @PathVariable String var) {
        JSONObject res = new JSONObject();
        res.put("code", 200);
        res.put("msg", "success");
        res.put("data", userService.getUserList());
        ValueOperations valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set("name1","junmooo1");
        Object o = valueOperations.get(var);
        System.out.println(o);
        return res;
    }
}
