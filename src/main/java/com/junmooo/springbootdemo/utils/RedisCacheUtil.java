package com.junmooo.springbootdemo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RedisCacheUtil {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //2.添加静态的变量
    public static RedisTemplate redis;

    public static StringRedisTemplate stringRedis;

    @PostConstruct
    public void getRedisTemplate() {
        redis = this.redisTemplate;
    }

    @PostConstruct
    public void getStringRedis() {
        stringRedis = this.stringRedisTemplate;
    }


    //1....其他的工具方法...
}