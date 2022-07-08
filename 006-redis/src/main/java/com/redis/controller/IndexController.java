package com.redis.controller;

import com.redis.annotation.RedisLock;
import com.redis.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YanZhao
 * @description
 * @date 2022年06月01日 12:20
 */

@RestController
public class IndexController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/")
    public String index() {
        return "Port:" + redisTemplate.getConnectionFactory().getConnection().ping();
    }

    @GetMapping("name")
    public String name() {
        redisTemplate.opsForValue().set("username", "张三");
        Object username = redisTemplate.opsForValue().get("username");
        return username.toString();
    }

    /***
     * 分布式锁的实现
     * @Parem: []
     * @return: java.lang.String
     */

    @RedisLock(key = "redis_lock")
    @GetMapping("/lock")
    public String distributeLock() {
        return "lock";
    }


    @Autowired
    private RedisUtils redisUtils;

    @GetMapping("age")
    public String age() {
        redisUtils.set("age", 18);
        return redisUtils.get("age").toString();

    }
}
