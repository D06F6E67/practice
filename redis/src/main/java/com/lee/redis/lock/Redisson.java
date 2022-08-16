package com.lee.redis.lock;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁  通过 org.redisson:redisson 实现
 *
 * @author Lee
 */
public class Redisson {
    private static final String LOCK_KEY = "COMMERCE-BUSINESS";

    /**
     * Redisson的配置类
     */
    private static RedissonClient redissonClient() {
        Config config = new Config();
        /* Redis 单节点*/
        config.useSingleServer()
                .setAddress("redis://192.168.31.21:6379")
                .setPassword("bmy@1688");
        return org.redisson.Redisson.create(config);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            // new Thread(() -> lockMethod()).start();
            new Thread(() -> lockMethodWithRetry()).start();
        }
    }

    /**
     * 基本使用
     */
    private static void lockMethod() {
        RedissonClient redissonClient = redissonClient();
        /* RLock extends Lock*/
        RLock lock = redissonClient.getLock(LOCK_KEY);

        /*可重入锁： 默认超时时间为30s*/
        // while (!lock.tryLock()) {
        //     try {
        //         Thread.sleep(5 * 1000);
        //     } catch (InterruptedException e) {
        //         throw new RuntimeException(e);
        //     }
        //     System.out.println(Thread.currentThread().getName() + "无法获得锁");
        // }

        if (lock.tryLock()) {
            System.out.println(Thread.currentThread().getName() + "获得锁");

            executeBusiness();

            lock.unlock();
            System.out.println(Thread.currentThread().getName() + "释放锁");

        }

    }

    /**
     * 等待超时的锁
     */
    private static void lockMethodWithRetry() {
        RedissonClient redissonClient = redissonClient();
        /*获取对应的key的锁*/
        RLock lock = redissonClient.getLock(LOCK_KEY);

        // RLock multiLock = redissonClient.getMultiLock(lock1, lock2);

        // 内部包含 重试机制，通过Redis的发布订阅者模式来实现
        /* 参数一：最长等待时间，超时则不再等待
         * 参数二：锁超时释放时间
         * 参数三：时间单位 */
        boolean hasLok = false;
        try {
            hasLok = lock.tryLock(200, 10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (hasLok) {
            try {
                System.out.println(Thread.currentThread().getName() + "获得锁");
                executeBusiness();

                lock.unlock();
                System.out.println(Thread.currentThread().getName() + "释放锁");
                Thread.interrupted();
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println(Thread.currentThread().getName() + "停止");
                Thread.interrupted();
            // } finally {
            }
        // } else {
        //     System.out.println("Can not get lock");
        }
    }

    private static void executeBusiness() {
        System.out.println(Thread.currentThread().getName() + "开始业务..." + System.currentTimeMillis());
        try {
            if (Thread.currentThread().getName().contains("8")) {
                // Thread.sleep(5 * 1000);
                throw new RuntimeException();
            } else {
                Thread.sleep(2 * 1000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + "结束业务..." + System.currentTimeMillis());
    }
}
