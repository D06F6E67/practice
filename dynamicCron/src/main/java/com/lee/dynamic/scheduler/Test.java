package com.lee.dynamic.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component("test")
public class Test {

    private static final Logger LOGGER = LoggerFactory.getLogger(Test.class);

    public void test() {
        LOGGER.info("test: 时间：{}",  LocalDateTime.now());
    }

    public void test(String s, Boolean b, Long l, Double d, Integer i) {
        LOGGER.info("执行多参方法： 字符串类型{}，布尔类型{}，长整型{}，浮点型{}，整形{} 。 时间：{}", s, b, l, d, i, LocalDateTime.now());
    }

}
