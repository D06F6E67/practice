package com.lee.redis.lock;

import redis.clients.jedis.Jedis;

/**
 * 分布式锁  通过 set nx 实现
 *
 * @author Lee
 */
public class SetNX {
    private static String REDIS_IP = "192.168.31.21";
    private static Integer REDIS_PORT = 6379;
    private static String REDIS_AUTH = "bmy@1688";

    public static final String LOCK_NAME = "LOCK";
    public static final String LOCK_VALUE = "ERICK";
    public static final int EXPIRE_SECS = 3;


    private static Jedis getJedis() {
        Jedis jedis = new Jedis(REDIS_IP, REDIS_PORT);
        jedis.auth(REDIS_AUTH);
        return jedis;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            // new Thread(() -> firstLock()).start();
            // new Thread(() -> secondLock()).start();
            new Thread(() -> thirdLock()).start();
        }
    }

    /**
     * 场景一: 假如释放锁失败，则后面永远无法执行
     */
    public static void firstLock() {
        Jedis jedis = getJedis();
        // 1.获取锁
        Long lock;
        do {
            lock = jedis.setnx(LOCK_NAME, LOCK_VALUE);

            if (1 == lock) {
                if (Thread.currentThread().getName().contains("8")) {
                    // 模拟某个线程出现异常，无法释放锁
                    throw new RuntimeException();
                }
                // 2.执行业务
                executeBusiness();
                // 3.释放锁
                jedis.del(LOCK_NAME);
                break;
            } else {
                System.out.println(Thread.currentThread().getName() + "获取失败");
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } while (lock != 1);

    }

    /**
     * 场景二： 释放锁失败，通过自动过期来保证
     */
    public static void secondLock() {
        Jedis jedis = getJedis();
        // 1.获取锁
        String lock;
        do {
            lock = jedis.set(LOCK_NAME, LOCK_VALUE, "NX", "EX", EXPIRE_SECS);
            if ("OK".equalsIgnoreCase(lock)) {
                // if (Thread.currentThread().getName().contains("8")) {
                //     throw new RuntimeException();
                // }
                // 2.执行业务
                executeBusiness();
                System.out.println(Thread.currentThread().getName() + "释放锁" + System.currentTimeMillis());
                // 3.释放锁
                jedis.del(LOCK_NAME);
                break;
            } else {
                // System.out.println(Thread.currentThread().getName() + "获取失败");
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } while (!"OK".equalsIgnoreCase(lock));
    }

    /**
     * 场景三： 业务时间大于锁过期时间
     */
    public static void thirdLock() {
        Jedis jedis = getJedis();
        // 1.获取锁
        String lock;
        do {
            lock = jedis.set(LOCK_NAME, Thread.currentThread().getName(), "NX", "EX", EXPIRE_SECS);
            if ("OK".equalsIgnoreCase(lock)) {
                // 2.执行业务
                executeBusiness();

                // 只释放自己的锁
                if (Thread.currentThread().getName().equals(jedis.get(LOCK_NAME))) {
                    System.out.println(Thread.currentThread().getName() + "释放锁" + System.currentTimeMillis());
                    // 3.释放锁
                    jedis.del(LOCK_NAME);
                }
                break;
            } else {
                // System.out.println(Thread.currentThread().getName() + "获取失败");
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } while (!"OK".equalsIgnoreCase(lock));
    }

    private static void executeBusiness() {
        System.out.println(Thread.currentThread().getName() + "开始业务..." + System.currentTimeMillis());
        try {
            if (Thread.currentThread().getName().contains("8")) {
                // 模拟场景二三中，业务时间大于锁过期时间
                Thread.sleep(5 * 1000);
            } else {
                Thread.sleep(2 * 1000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + "结束业务..." + System.currentTimeMillis());
    }
}
