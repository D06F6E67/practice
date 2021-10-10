package com.lee.aop.encryption;

import java.lang.annotation.*;

/**
 * 自定义加密注解
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Encry {
    String value();
}
