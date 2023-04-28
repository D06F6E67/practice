package com.lee.dynamic.scheduler.job;

import com.lee.dynamic.scheduler.entity.ScheduleJob;
import com.lee.dynamic.scheduler.util.JobInvokeUtil;
import org.quartz.JobExecutionContext;

/**
 * 定时任务处理（允许并发执行）
 *
 * @author ruoyi
 */
public class QuartzJobExecution extends AbstractQuartzJob {
    @Override
    protected void doExecute(JobExecutionContext context, ScheduleJob job) throws Exception {
        JobInvokeUtil.invokeMethod(job);
    }
}
