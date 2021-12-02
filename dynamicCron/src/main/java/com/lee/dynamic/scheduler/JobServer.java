package com.lee.dynamic.scheduler;

import com.lee.dynamic.scheduler.util.ScheduleUtils;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 任务处理
 */
@Service
public class JobServer {

    @Autowired
    private Scheduler scheduler;

    public String add(JobBean job) throws SchedulerException {
        ScheduleUtils.addScheduleJob(scheduler, job);
        return "OK";
    }
}
