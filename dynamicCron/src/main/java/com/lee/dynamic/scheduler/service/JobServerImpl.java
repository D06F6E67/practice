package com.lee.dynamic.scheduler.service;

import com.lee.dynamic.scheduler.entity.ScheduleJob;
import com.lee.dynamic.scheduler.util.QuartzManager;
import org.springframework.stereotype.Service;

/**
 * 任务处理
 *
 * @author lee
 */
@Service
public class JobServerImpl implements JobServer {

    private QuartzManager quartzManager;

    public JobServerImpl(QuartzManager quartzManager) {
        this.quartzManager = quartzManager;
        this.quartzManager.start();
    }


    @Override
    public String add(ScheduleJob job) {
        quartzManager.addJob(job);
        return "OK";
    }

    @Override
    public String delete(ScheduleJob job) {
        quartzManager.deleteJob(job);
        return "OK";
    }

    @Override
    public String update(ScheduleJob job) {
        quartzManager.updateJobCron(job);
        return "OK";
    }

    @Override
    public String pause(ScheduleJob job) {
        quartzManager.pauseJob(job);
        return "OK";
    }

    @Override
    public String resume(ScheduleJob job) {
        quartzManager.resumeJob(job);
        return "OK";
    }

    @Override
    public String run(ScheduleJob job) {
        quartzManager.runAJobNow(job);
        return "OK";
    }
}
