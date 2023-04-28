package com.lee.dynamic.scheduler.job;

import com.lee.dynamic.scheduler.entity.ScheduleJob;
import com.lee.dynamic.scheduler.util.QuartzManager;
import lombok.extern.log4j.Log4j2;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.BeanUtils;

/**
 * 抽象quartz调用
 *
 * @author ruoyi
 */
@Log4j2
public abstract class AbstractQuartzJob implements Job {

    @Override
    public void execute(JobExecutionContext context) {

        ScheduleJob job = new ScheduleJob();
        BeanUtils.copyProperties(context.getMergedJobDataMap().get(QuartzManager.TASK_PROPERTIES), job);

        try {
            doExecute(context, job);
        } catch (Exception e) {
            log.error("任务执行异常  - ：", e);
        }
    }


    /**
     * 执行方法，由子类重载
     *
     * @param context 工作执行上下文对象
     * @param job     系统计划任务
     * @throws Exception 执行过程中的异常
     */
    protected abstract void doExecute(JobExecutionContext context, ScheduleJob job) throws Exception;
}
