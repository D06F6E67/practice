package com.lee.redis.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisDemo {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping()
    public void contextLoads() {
        // 获取redis连接对象
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        connection.flushDb();

        // 字符操作
        redisTemplate.opsForValue().append("key1", "test");
        System.out.println(redisTemplate.opsForValue().get("key1"));
        //List操作
        //redisTemplate.opsForList();
        //Set操作
        //redisTemplate.opsForSet();
        //balabala好多操作
    }
}
