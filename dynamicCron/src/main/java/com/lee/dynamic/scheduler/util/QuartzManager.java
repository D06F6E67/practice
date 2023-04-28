package com.lee.dynamic.scheduler.util;

import com.lee.dynamic.scheduler.job.QuartzDisallowConcurrentExecution;
import com.lee.dynamic.scheduler.job.QuartzJobExecution;
import com.lee.dynamic.scheduler.entity.ScheduleJob;
import org.quartz.*;
import org.quartz.DateBuilder.IntervalUnit;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 计划任务管理
 *
 * @author lee
 */
@Service
public class QuartzManager {

    @Resource
    private Scheduler scheduler;
    /**
     * 执行目标key
     */
    public static final String TASK_PROPERTIES = "TASK_PROPERTIES";


    public void start() {
        try {
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 添加任务
     *
     * @param job 任务
     */
    public void addJob(ScheduleJob job) {

        try {
            // 创建jobDetail实例，绑定Job实现类
            // 指明job的名称，所在组的名称，以及绑定job类
            Class<? extends Job> jobClass = job.getConcurrent() ?
                    QuartzJobExecution.class : QuartzDisallowConcurrentExecution.class;

            JobDetail jobDetail = JobBuilder.newJob(jobClass)
                    // 任务名称和组构成任务key
                    .withIdentity(job.getJobName(), job.getJobGroup())
                    .build();
            // 定义调度触发规则
            // 使用cornTrigger规则
            Trigger trigger = TriggerBuilder.newTrigger()
                    // 触发器key
                    .withIdentity(job.getJobName(), job.getJobGroup())
                    .startAt(DateBuilder.futureDate(1, IntervalUnit.SECOND))
                    .withSchedule(CronScheduleBuilder.cronSchedule(job.getCronExpression())).startNow().build();

            // 任务保存到JobDetail中
            jobDetail.getJobDataMap().put(QuartzManager.TASK_PROPERTIES, job);
            // 把作业和触发器注册到任务调度中
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除一个job
     *
     * @param scheduleJob
     */
    public void deleteJob(ScheduleJob scheduleJob) {
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        try {
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 更新job时间表达式
     *
     * @param scheduleJob
     */
    public void updateJobCron(ScheduleJob scheduleJob) {
        deleteJob(scheduleJob);
        addJob(scheduleJob);
    }


    /**
     * 暂停一个job
     *
     * @param scheduleJob
     */
    public void pauseJob(ScheduleJob scheduleJob) {
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        try {
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 恢复一个job
     *
     * @param scheduleJob
     */
    public void resumeJob(ScheduleJob scheduleJob) {
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        try {
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 立即执行job
     *
     * @param scheduleJob
     */
    public void runAJobNow(ScheduleJob scheduleJob) {
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        try {
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }
}