package com.example.demo.service;

import com.example.demo.bean.User;
import com.example.demo.dao.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class HandleRedis {
    @Autowired
    private RedisUtil redisUtil;

    public HandleRedis(){
    }
    public HandleRedis(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }


    public void setup(){
        User user = new User();
        user.setName("zml");
        user.setAge(16);
        user.setTime(new Date());
        redisUtil.set("this.getClass().getName()",user);
        System.out.println(redisUtil.get("this.getClass().getName()"));

        User user2 = new User();
        user2.setName("lmz");
        user2.setAge(16);
        user2.setTime(new Date());
        redisUtil.set("this.getClass().getName()",user2);
        System.out.println(redisUtil.get("this.getClass().getName()"));

        redisUtil.del("this.getClass().getName()");
        System.out.println(redisUtil.get("this.getClass().getName()"));
    }
}
