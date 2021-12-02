package com.lee.dynamic.scheduler.job;

import com.lee.dynamic.scheduler.JobBean;
import com.lee.dynamic.scheduler.util.JobInvokeUtil;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;

/**
 * 定时任务处理（禁止并发执行）
 */
@DisallowConcurrentExecution
public class QuartzDisallowConcurrentExecution extends AbstractQuartzJob {
    @Override
    protected void doExecute(JobExecutionContext context, JobBean job) throws Exception {
        JobInvokeUtil.invokeMethod(job);
    }
}
