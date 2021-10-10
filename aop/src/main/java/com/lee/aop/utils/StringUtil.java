package com.lee.aop.utils;

/**
 * 字符串工具类
 */
public class StringUtil {

    /**
     * 支付串脱敏
     *
     * @param data 脱敏数据
     * @return 脱敏后数据
     */
    public static String hide(String data) {
        if (data == null) return "";
        int length = data.length();
        if (length <= 1) {
            return data;
        } else if (length <= 2) {
            return data.substring(0, 1) + "**";
        } else {
            return data.substring(0, 1) + "**" + data.substring(length - 1);
        }
    }
}
