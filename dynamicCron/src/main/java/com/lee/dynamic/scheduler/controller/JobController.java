package com.lee.dynamic.scheduler.controller;

import com.lee.dynamic.scheduler.entity.ScheduleJob;
import com.lee.dynamic.scheduler.service.JobServer;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 定时任务操作
 * @author lee
 */
@RestController
@RequestMapping("/job")
public class JobController {

    @Resource
    private JobServer jobServer;

    /**
     * 添加定时任务
     */
    @PutMapping
    public String add(@RequestBody ScheduleJob job) {
        return jobServer.add(job);
    }

    /**
     * 删除定时任务
     */
    @DeleteMapping
    public String delete(@RequestBody ScheduleJob job) {
        return jobServer.delete(job);
    }

    /**
     * 修改定时任务
     */
    @PostMapping
    public String update(@RequestBody ScheduleJob job) {
        return jobServer.update(job);
    }

    /**
     * 暂停定时任务
     */
    @PostMapping("/pause")
    public String pause(@RequestBody ScheduleJob job) {
        return jobServer.pause(job);
    }

    /**
     * 恢复定时任务
     */
    @PostMapping("/resume")
    public String resume(@RequestBody ScheduleJob job) {
        return jobServer.resume(job);
    }

    /**
     * 立即执行定时任务
     */
    @PostMapping("/run")
    public String run(@RequestBody ScheduleJob job) {
        return jobServer.run(job);
    }
}
