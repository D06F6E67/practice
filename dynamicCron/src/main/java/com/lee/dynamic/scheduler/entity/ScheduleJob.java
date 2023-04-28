package com.lee.dynamic.scheduler.entity;

import lombok.Data;

/**
 * 任务实体
 *
 * @author lee
 */
@Data
public class ScheduleJob {
    /**
     * 任务名称
     */
    private String jobName;
    /**
     * 任务分组
     */
    private String jobGroup;
    /**
     * cron表达式
     */
    private String cronExpression;
    /**
     * 调用目标字符串
     */
    private String invokeTarget;
    /**
     * 是否并发执行
     */
    private Boolean concurrent;
}
