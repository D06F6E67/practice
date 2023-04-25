package com.lee.sharding;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 检查日期
 *
 * @author Lee
 */
@Documented
@Retention(RUNTIME)
@Target({METHOD,PARAMETER,FIELD})
public @interface ExamineDate {
}
