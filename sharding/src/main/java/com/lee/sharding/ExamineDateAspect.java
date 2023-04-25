package com.lee.sharding;

import com.lee.sharding.util.DateUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Objects;

/**
 * 检查日期切面
 * 日期为空就设置为当前日期
 *
 * @author Lee
 */
@Aspect
@Component
public class ExamineDateAspect {

    @Before("@annotation(ExamineDate)")
    public void before(JoinPoint point) {
        Object[] args = point.getArgs();

        for (Object arg : args) {
            for (Field field : arg.getClass().getDeclaredFields()) {
                if (Date.class.equals(field.getType())
                        && Objects.nonNull(field.getAnnotation(ExamineDate.class))) {
                    if (dateIsNull(arg, field.getName())) {
                        setDate(arg, field.getName());
                    }
                }
            }
        }
    }

    /**
     * 对象字段值是否唯恐
     *
     * @param arg  对象
     * @param name 字段名
     * @return 结果
     */
    private static boolean dateIsNull(Object arg, String name) {

        try {
            return Objects.isNull(
                    arg.getClass()
                            .getMethod(getMethodName("get", name))
                            .invoke(arg));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 设置时间
     *
     * @param arg  对象
     * @param name 字段名
     */
    private static void setDate(Object arg, String name) {

        try {
            arg.getClass()
                    .getMethod(getMethodName("set", name), Date.class)
                    .invoke(arg, getDate(name));
        } catch (Exception ignored) {

        }
    }

    /**
     * 获取方法名
     *
     * @param getOrSet get或者set方法
     * @param name     字段名
     * @return 方法名
     */
    private static String getMethodName(String getOrSet, String name) {
        return String.format("%s%s%s", getOrSet, name.substring(0, 1).toUpperCase(), name.substring(1));
    }

    /**
     * 获取时间
     *
     * @param name 字段名
     * @return 时间
     */
    private static Date getDate(String name) {

        if (name.contains("start")) {
            return DateUtil.getMonthFirstDay(new Date());
        }

        if (name.contains("end")) {
            return DateUtil.getMonthLastDay(new Date());
        }

        return new Date();
    }
}
