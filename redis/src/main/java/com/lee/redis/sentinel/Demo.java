package com.lee.redis.sentinel;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/sentinel")
public class Demo {

    //连接池配置
    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

    //哨兵信息
    Set<String> sentinels = new HashSet<String>(Arrays.asList(
            "192.168.31.21:26380",
            "192.168.31.21:26381",
            "192.168.31.21:26382"
    ));


    @GetMapping("/set/{key}/{velue}")
    public String set(@PathVariable("key") String key, @PathVariable("velue") String velue) {
        // 最大连接数
        jedisPoolConfig.setMaxTotal(10);
        // 空闲数
        jedisPoolConfig.setMaxIdle(5);
        jedisPoolConfig.setMinIdle(5);
        //创建连接池
        //mymaster是我们配置给哨兵的服务名称
        //sentinels是哨兵信息
        //jedisPoolConfig是连接池配置
        //abcdefg是连接Redis服务器的密码
        JedisSentinelPool pool = new JedisSentinelPool("mymaster", sentinels, jedisPoolConfig);
        //获取客户端
        Jedis jedis = pool.getResource();
        //执行两个命令
        jedis.set(key, velue);
        return jedis.get(key);
//        //打印信息
//        System.out.println(myvalue);

    }

    @GetMapping("/get/{key}")
    public String get(@PathVariable("key") String key) {
        // 最大连接数
        jedisPoolConfig.setMaxTotal(10);
        // 空闲数
        jedisPoolConfig.setMaxIdle(5);
        jedisPoolConfig.setMinIdle(5);
        //创建连接池
        //mymaster是我们配置给哨兵的服务名称
        //sentinels是哨兵信息
        //jedisPoolConfig是连接池配置
        //abcdefg是连接Redis服务器的密码
        JedisSentinelPool pool = new JedisSentinelPool("mymaster", sentinels, jedisPoolConfig);
        //获取客户端
        Jedis jedis = pool.getResource();
//        //执行两个命令
//        jedis.set(key, velue);
        return jedis.get(key);
//        //打印信息
//        System.out.println(myvalue);

    }

}
