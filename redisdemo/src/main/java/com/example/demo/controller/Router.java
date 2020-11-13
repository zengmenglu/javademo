package com.example.demo.controller;

import com.example.demo.dao.RedisUtil;
import com.example.demo.service.HandleRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class Router {
    @Resource
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/ping")
    public void ping(){
        System.out.println("pong");
    }

    @RequestMapping("/pararedis")
    @ResponseBody
    public String pararedis(String name, String value){
        System.out.println("name:"+name+",value:"+value);
        redisTemplate.opsForValue().set(name, value);
        redisTemplate.opsForValue().set("name", "value");
        return (String)redisTemplate.opsForValue().get(name);
    }

    @GetMapping("/redis")
    public String redis(){
        redisTemplate.opsForValue().set("name", "value");
        return (String)redisTemplate.opsForValue().get("name");
    }

    @GetMapping("/funcredis")
    public void funcredis(){
        HandleRedis handleRedis = new HandleRedis(redisUtil);
        handleRedis.setup();
    }



    @GetMapping("/direcredis")
    public String direcredis(){
        redisUtil.set("xxx","yyy");
        return (String) redisUtil.get("xxx");
    }
}
