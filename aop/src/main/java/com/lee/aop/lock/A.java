package com.lee.aop.lock;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lee
 */
@Service
public class A {

    @Resource
    private SharedResource sharedResource;

    public void executeA() throws InterruptedException {
        sharedResource.setExecuting(true);
        sharedResource.stop();
        System.out.println("A 开始处理");
        Thread.sleep(5000);
        System.out.println("A 处理完成");
        sharedResource.setExecuting(false);
    }
}
