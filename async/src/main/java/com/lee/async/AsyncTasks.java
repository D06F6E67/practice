package com.lee.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class AsyncTasks {

    public static Random random = new Random();

    @Async("task2")
    public CompletableFuture<String> doTaskOne() throws Exception {
        log.info("开始做任务一");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        log.info("完成任务一，耗时：" + (end - start) + "毫秒");
        return CompletableFuture.completedFuture("任务一完成");
    }

    @Async("task1")
    public CompletableFuture<String> doTaskTwo() throws Exception {
        log.info("开始做任务二");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        log.info("完成任务二，耗时：" + (end - start) + "毫秒");
        return CompletableFuture.completedFuture("任务二完成");
    }

    @Async
    public CompletableFuture<String> doTaskThree() throws Exception {
        log.info("开始做任务三");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        log.info("完成任务三，耗时：" + (end - start) + "毫秒");
        return CompletableFuture.completedFuture("任务三完成");
    }

    @Async("task2")
    public void test(AsyncVo<String, Object> vo) {
        try {
            String params = vo.getParams();
            log.info("开始处理 = {}", params);
            Thread.sleep(2000);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("params", params);
            map.put("time", System.currentTimeMillis());

            vo.getResult().setResult(map);
            log.info("处理完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
