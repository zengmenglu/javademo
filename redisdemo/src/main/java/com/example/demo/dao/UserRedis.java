package com.example.demo.dao;

import com.example.demo.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


public class UserRedis {
    @Resource
    private RedisTemplate<Object, User> redisTemplate;
    public void add(String key,User user){
        redisTemplate.opsForValue().set(key,user);
    }
    public User get(String key){
        return redisTemplate.opsForValue().get(key);
    }
    public void delete(String key){
        redisTemplate.delete(key);
    }
    public void update(String key, User user){
        redisTemplate.opsForValue().set(key,user);
    }
}