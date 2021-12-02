package com.lee.dynamic.scheduler.util;

import com.lee.dynamic.scheduler.JobBean;
import com.lee.dynamic.scheduler.job.QuartzDisallowConcurrentExecution;
import com.lee.dynamic.scheduler.job.QuartzJobExecution;
import org.quartz.*;

/**
 * 定时任务工具类
 */
public class ScheduleUtils {
    public static final String TASK_CLASS_NAME = "TASK_CLASS_NAME";
    /**
     * 执行目标key
     */
    public static final String TASK_PROPERTIES = "TASK_PROPERTIES";

    /**
     * 获取定时任务类
     */
    private static Class<? extends Job> getJobClass(JobBean job) {
        return job.getConcurrent() ? QuartzJobExecution.class : QuartzDisallowConcurrentExecution.class;
    }


    /**
     * 添加定时任务
     *
     * @param scheduler
     * @param job
     */
    public static void addScheduleJob(Scheduler scheduler, JobBean job) throws SchedulerException {
        Class<? extends Job> jobClass = getJobClass(job);

        // 构建job信息
        Long jobId = job.getJobId();
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(getJobKey(jobId)).build();

        // 表达式调度构建器
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());

        // 按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(TriggerKey.triggerKey(TASK_CLASS_NAME + jobId))
                .withSchedule(cronScheduleBuilder).build();

        // 放入参数，运行时的方法可以获取
        jobDetail.getJobDataMap().put(TASK_PROPERTIES, job);

        // 判断是否存在
        if (scheduler.checkExists(getJobKey(jobId))) {
            // 防止创建时存在数据问题 先移除，然后在执行创建操作
            scheduler.deleteJob(getJobKey(jobId));
        }

        scheduler.scheduleJob(jobDetail, trigger);
    }

    /**
     * 构建任务键对象
     */
    public static JobKey getJobKey(Long jobId) {
        return JobKey.jobKey(TASK_CLASS_NAME + jobId);
    }

}
