package com.lee.dynamic.scheduler.util;

import com.lee.dynamic.scheduler.entity.ScheduleJob;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * 任务执行工具
 *
 * @author ruoyi
 */
public class JobInvokeUtil {
    /**
     * 执行方法
     *
     * @param job 系统任务
     */
    public static void invokeMethod(ScheduleJob job) throws Exception {

        String invokeTarget = job.getInvokeTarget();
        String beanPath = StringUtils.substringBefore(invokeTarget, "(");
        String beanName = getBeanName(beanPath);
        String methodName = getMethodName(beanPath);
        List<Object[]> methodParams = getMethodParams(invokeTarget);

        Object bean;
        if (isValidClassName(beanPath)) {
            bean = Class.forName(beanName).newInstance();
        } else {
            bean = SpringUtils.getBean(beanName);
        }
        invokeMethod(bean, methodName, methodParams);
    }

    /**
     * 调用任务方法
     *
     * @param bean         目标对象
     * @param methodName   方法名称
     * @param methodParams 方法参数
     */
    private static void invokeMethod(Object bean, String methodName, List<Object[]> methodParams)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {

        if (Objects.nonNull(methodParams) && methodParams.size() > 0) {
            Method method = bean.getClass().getDeclaredMethod(methodName, getMethodParamsType(methodParams));
            method.invoke(bean, getMethodParamsValue(methodParams));
        } else {
            Method method = bean.getClass().getDeclaredMethod(methodName);
            method.invoke(bean);
        }
    }

    /**
     * 获取bean名称
     *
     * @param beanPath 目标字符串
     * @return bean名称
     */
    public static String getBeanName(String beanPath) {
        return StringUtils.substringBeforeLast(beanPath, ".");
    }

    /**
     * 获取bean方法
     *
     * @param beanPath 目标字符串
     * @return method方法
     */
    public static String getMethodName(String beanPath) {
        return StringUtils.substringAfterLast(beanPath, ".");
    }

    /**
     * 获取method方法参数相关列表
     *
     * @param invokeTarget 目标字符串
     * @return method方法相关参数列表
     */
    public static List<Object[]> getMethodParams(String invokeTarget) {
        String methodStr = StringUtils.substringBetween(invokeTarget, "(", ")");
        if (StringUtils.isEmpty(methodStr)) {
            return null;
        }
        String[] methodParams = methodStr.split(",(?=([^\"']*[\"'][^\"']*[\"'])*[^\"']*$)");
        List<Object[]> objects = new LinkedList<>();
        for (int i = 0; i < methodParams.length; i++) {
            String str = StringUtils.trimToEmpty(methodParams[i]);
            // String字符串类型，以'或"开头
            if (StringUtils.startsWithAny(str, "'", "\"")) {
                objects.add(new Object[]{StringUtils.substring(str, 1, str.length() - 1), String.class});
            }
            // boolean布尔类型，等于true或者false
            else if ("true".equalsIgnoreCase(str) || "false".equalsIgnoreCase(str)) {
                objects.add(new Object[]{Boolean.valueOf(str), Boolean.class});
            }
            // long长整形，以L结尾
            else if (StringUtils.endsWith(str, "L")) {
                objects.add(new Object[]{Long.valueOf(StringUtils.substring(str, 0, str.length() - 1)), Long.class});
            }
            // double浮点类型，以D结尾
            else if (StringUtils.endsWith(str, "D")) {
                objects.add(new Object[]{Double.valueOf(StringUtils.substring(str, 0, str.length() - 1)), Double.class});
            }
            // 其他类型归类为整形
            else {
                objects.add(new Object[]{Integer.valueOf(str), Integer.class});
            }
        }
        return objects;
    }

    /**
     * 校验是否为为class包名
     *
     * @param beanPath 名称
     * @return true是 false否
     */
    public static boolean isValidClassName(String beanPath) {
        return Pattern.matches("(\\w+\\.){2,}\\w+", beanPath);
    }

    /**
     * 获取参数类型
     *
     * @param methodParams 参数相关列表
     * @return 参数类型列表
     */
    public static Class<?>[] getMethodParamsType(List<Object[]> methodParams) {

        Class<?>[] classs = new Class<?>[methodParams.size()];
        int index = 0;
        for (Object[] os : methodParams) {
            classs[index] = (Class<?>) os[1];
            index++;
        }
        return classs;
    }

    /**
     * 获取参数值
     *
     * @param methodParams 参数相关列表
     * @return 参数值列表
     */
    public static Object[] getMethodParamsValue(List<Object[]> methodParams) {

        Object[] classs = new Object[methodParams.size()];
        int index = 0;
        for (Object[] os : methodParams) {
            classs[index] = os[0];
            index++;
        }
        return classs;
    }
}
