package com.lee.dynamic.pool.bean;

import lombok.Data;

/**
 * 任务实体Bean
 */
@Data
public class TaskBean {
    /**
     * 任务key值 唯一
     */
    private String taskKey;
    /**
     * 任务Bean
     */
    private String taskBean;
    /**
     * 任务表达式
     */
    private String taskCron;
    /**
     * 程序初始化是否启动 1 是 0 否
     */
    private Integer initStartFlag;
    /**
     * 当前是否已启动
     */
    private boolean startFlag;
}
