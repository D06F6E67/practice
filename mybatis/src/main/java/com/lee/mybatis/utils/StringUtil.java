package com.lee.mybatis.utils;

public class StringUtil {

    public static Boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static Boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static void print(Object obj) {
        System.out.println(obj);
    }
}
