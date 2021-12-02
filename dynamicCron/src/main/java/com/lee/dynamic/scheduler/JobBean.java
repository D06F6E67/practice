package com.lee.dynamic.scheduler;

import lombok.Data;

/**
 * 任务实体
 */
@Data
public class JobBean {
    /**
     * 任务ID
     */
    private Long jobId;
    /**
     * 任务名称
     */
    private String jobName;
    /**
     * 调用目标字符串
     */
    private String invokeTarget;
    /**
     * cron执行表达式
     */
    private String cronExpression;
    /**
     * 是否并发执行
     */
    private Boolean concurrent;

}
