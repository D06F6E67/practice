package com.lee.redis.lock;

import redis.clients.jedis.Jedis;

import java.util.Arrays;

/**
 * 分布式锁  通过 Lua脚本 实现
 *
 * @author Lee
 */
public class Lua {
    private static String REDIS_IP = "192.168.31.21";
    private static Integer REDIS_PORT = 6379;
    private static String REDIS_AUTH = "bmy@1688";

    public static final String LOCK_NAME = "LOCK";
    public static final String LOCK_VALUE = "ERICK";


    private static Jedis getJedis() {
        Jedis jedis = new Jedis(REDIS_IP, REDIS_PORT);
        jedis.auth(REDIS_AUTH);
        return jedis;
    }

    public static void main(String[] args) {

        System.out.println(deleteLockIfMy(getJedis(), LOCK_NAME, LOCK_VALUE));

    }

    /**
     * 释放锁
     *
     * @param lockKey 锁名称
     * @param lockValue 锁值
     * @return 释放结果
     */
    private static boolean deleteLockIfMy(Jedis redis, String lockKey, String lockValue) {

        /*
            # 1. Redis内部函数
            redis.call('命令名称','key','其他参数', ......)

            # 2. 无参数， 0代表key的参数
            EVAL "return redis.call('set','name','erick')" 0

            # 3. 带参数
            EVAL "return redis.call('set',KEYS[1],ARGV[1])" 1 age 20
            KEYS[1]:  redis的key值个数
            ARGV[1]:  redis的value的值个数
            1:        具体包含几个key
            age：     实际传递的key值
            20:       实际传递的value值
         */

        /*用lua脚本来保证*/
        String luaScript =
                // -- 获取锁中的线程标示,动态传递参数
                "local keyName = redis.call('get',KEYS[1])\n" +
                //-- 比较线程标示与锁中的是否一直
                "if (keyName == ARGV[1]) then\n" +
                //   -- 释放锁
                "    redis.call('del',KEYS[1])\n" +
                "    return 1\n" +
                //-- 如果不一致，则返回结果为0
                "else\n" +
                "    return 0\n" +
                "end";

        /*加载脚本*/
        String script = redis.scriptLoad(luaScript);
        /*向脚本中传递参数*/
        Object delResult = redis.evalsha(script, Arrays.asList(lockKey), Arrays.asList(lockValue));
        /*上面的结果是Long类型*/
        if (delResult.equals(1L)) {
            return true;
        } else {
            return false;
        }
    }


}
