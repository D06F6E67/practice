package com.lee.redis.jedis;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * jedis事务
 */
public class JedisTrans {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.31.21", 6379);
        jedis.auth("root");
        System.out.println("Jedis.ping():" + jedis.ping());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name1", "alice");
        jsonObject.put("name2", "bob");

        //开启事务
        Transaction multi = jedis.multi();
        String result = jsonObject.toJSONString();
//        jedis.flushDB();
        jedis.watch(result);
        try {
            multi.set("user1", result);
            multi.set("user2", result);
            multi.exec(); //执行事务
        } catch (Exception e) {
            multi.discard();//放弃事务
            e.printStackTrace();
        } finally {
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));
            jedis.close();//关闭连接
        }
    }
}
