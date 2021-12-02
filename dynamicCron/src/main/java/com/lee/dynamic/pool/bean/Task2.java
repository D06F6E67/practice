package com.lee.dynamic.pool.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class Task2 implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(Task2.class);

    @Override
    public void run() {
        LOGGER.info("Task2 当前线程名称 {} 时间{}", Thread.currentThread().getName(), LocalDateTime.now());
    }
}
