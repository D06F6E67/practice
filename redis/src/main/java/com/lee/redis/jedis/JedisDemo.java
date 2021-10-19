package com.lee.redis.jedis;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * jedis基本使用
 */
public class JedisDemo {

    private static String REDIS_IP = "192.168.31.21";
    private static Integer REDIS_PORT = 6379;
    private static String REDIS_AUTH = "root";


    public static void operateString(){
        System.out.println("-----------Jedis String 相关命令测试-----------");
        Jedis jedis = new Jedis(REDIS_IP,REDIS_PORT);
        jedis.auth(REDIS_AUTH);
        System.out.println("Jedis.ping():"+jedis.ping());
        jedis.set("key0","123456");
        System.out.println("jedis.get(key0)"+jedis.get("key0"));
        jedis.mset("key1","val1","key2","val2");
        System.out.println("jedis.get(key*)"+jedis.keys("key*"));
        System.out.println("jedis.get(key1)):"+jedis.get("key1"));
        System.out.println("返回key的长度："+jedis.strlen("key0"));
        System.out.println("追加字符串："+jedis.append("key0","app"));
        System.out.println("打印key0:"+jedis.get("key0"));
        System.out.println("---------------------------------------------");
        jedis.close();
    }

    public static void main(String[] args) {
        operateList();
        oprateHash();
    }
    public static void operateList(){
        System.out.println("-----------Jedis List 相关命令测试-----------");
        Jedis jedis = new Jedis(REDIS_IP,REDIS_PORT);
        jedis.auth(REDIS_AUTH);
        System.out.println("Jedis.ping():"+jedis.ping());
        jedis.del("list1");

        //从List尾部添加3个元素
        jedis.rpush("list1","alice","bob","cindy");

        System.out.println("获取类型："+jedis.type("list1"));
        System.out.println("遍历区间[0.-1],获取全部的元素："+jedis.lrange("list1",0,-1));
        System.out.println("获取List的长度："+jedis.llen("list1"));
        System.out.println("---------------------------------------------");
        jedis.close();
    }

    public static void oprateHash(){
        System.out.println("-----------Jedis Hash 相关命令测试-----------");
        Jedis jedis = new Jedis(REDIS_IP,REDIS_PORT);
        jedis.auth(REDIS_AUTH);

        jedis.del("config");
        jedis.hset("config","ip","127.0.0.1");
        System.out.println("获取Hash的Field关联的Value:"+jedis.hget("config","ip"));
        System.out.println("获取类型："+jedis.type("config"));

        //批量添加field-value对
        Map<String,String> configFields = new HashMap<>();
        configFields.put("port","8080");
        configFields.put("maxalive","3600");
        configFields.put("weight","1.0");
        //执行批量添加
        jedis.hmset("config",configFields);
        System.out.println("批量获取："+jedis.hgetAll("config"));
        System.out.println("获取所有的key:"+jedis.hkeys("config"));
        System.out.println("获取所有的val:"+jedis.hvals("config"));
        System.out.println("获取长度："+jedis.hlen("config"));
        System.out.println("---------------------------------------------");
        jedis.close();
    }
}
