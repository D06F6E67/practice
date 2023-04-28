package com.lee.dynamic.scheduler.job;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Log4j2
@Component("test")
public class Test {

    public void test() {
        log.info("test: 时间：{}", LocalDateTime.now());
    }

    public void test(String s, Boolean b, Long l, Double d, Integer i) {
        log.info("执行多参方法： 字符串类型{}，布尔类型{}，长整型{}，浮点型{}，整形{} 。 时间：{}", s, b, l, d, i, LocalDateTime.now());
    }
}
