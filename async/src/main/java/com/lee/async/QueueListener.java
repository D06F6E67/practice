package com.lee.async;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 队列监听器，初始化启动所有监听任务
 */
// @Component
public class QueueListener {
    @Autowired
    private TestTask testTask;

    /**
     * 初始化时启动监听请求队列
     */
    @PostConstruct
    public void init() {
        testTask.start();
    }

    /**
     * 销毁容器时停止监听任务
     */
    @PreDestroy
    public void destory() {
        testTask.setRunning(false);
    }
}
