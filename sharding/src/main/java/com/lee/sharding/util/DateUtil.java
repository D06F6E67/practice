package com.lee.sharding.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author Lee
 */
public class DateUtil {

    /**
     * 获取日期年月
     *
     * @param date 日期
     * @return 年月
     */
    public static String getSuffixByYearMonth(Date date) {
        return new SimpleDateFormat("yyyyMM").format(date);
    }

    /**
     * 获取月份第一天日期
     *
     * @param date 日期
     * @return 日期月份第一天日期
     */
    public static Date getMonthFirstDay(Date date) {

        Calendar calendar = getCalendar(date);
        setDayTime(calendar, 1, 0, 0, 0);

        return calendar.getTime();
    }

    /**
     * 获取月份最后一天日期
     *
     * @param date 日期
     * @return 日期月份最后一天日期
     */
    public static Date getMonthLastDay(Date date) {

        Calendar calendar = getCalendar(date);
        setDayTime(calendar, calendar.getActualMaximum(Calendar.DAY_OF_MONTH), 23, 59, 59);

        return calendar.getTime();
    }

    /**
     * 设置时间，天时分秒
     *
     * @param calendar  时间
     * @param date      天
     * @param hourOfDay 时
     * @param minute    分
     * @param second    秒
     */
    private static void setDayTime(Calendar calendar, int date, int hourOfDay, int minute,
                                   int second) {
        calendar.set(Calendar.DAY_OF_MONTH, date);
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
    }

    /**
     * 根据时间获取Calendar
     *
     * @param date 时间
     * @return Calendar
     */
    private static Calendar getCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
}
