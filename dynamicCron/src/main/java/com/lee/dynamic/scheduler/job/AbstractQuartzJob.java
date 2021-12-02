package com.lee.dynamic.scheduler.job;

import com.lee.dynamic.scheduler.JobBean;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 抽象quartz调用
 */
public abstract class AbstractQuartzJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

    }

    /**
     * 执行前
     *
     * @param context 工作执行上下文对象
     * @param job     系统计划任务
     */
    protected void before(JobExecutionContext context, JobBean job) {

    }

    /**
     * 执行后
     *
     * @param context 工作执行上下文对象
     * @param job 系统计划任务
     */
    protected void after(JobExecutionContext context, JobBean job, Exception e) {}

    /**
     * 执行方法，由子类重载
     *
     * @param context 工作执行上下文对象
     * @param job 系统计划任务
     * @throws Exception 执行过程中的异常
     */
    protected abstract void doExecute(JobExecutionContext context, JobBean job) throws Exception;

}
