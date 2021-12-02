package com.lee.dynamic;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDateTime;

/**
 * 静态的定时任务
 */
@Configuration //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling // 2.开启定时任务
public class staticCron {

//    @Scheduled(cron = "0/5 * * * * ?")
    private void test() {
        System.out.println("执行定时任务1: " + LocalDateTime.now());
    }
}
