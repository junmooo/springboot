package com.junmooo.springbootdemo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {

    @Autowired
    private static RedisTemplate redisTemplate;

    @Autowired
    private static StringRedisTemplate stringRedisTemplate;

    public RedisUtils(StringRedisTemplate stringRedisTemplate, RedisTemplate redisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.redisTemplate = redisTemplate;
    }

    public static String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public static void set(String key, String value, int timeout, TimeUnit unit) {
        stringRedisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    public static void del(String key) {
        stringRedisTemplate.delete(key);
    }

    public static void hset(String key,String name,String value) {
        redisTemplate.opsForHash().put(key,name,value);
    }

    public static String hget(String key,String name) {
        return String.valueOf(redisTemplate.opsForHash().get(key,name));
    }

}
