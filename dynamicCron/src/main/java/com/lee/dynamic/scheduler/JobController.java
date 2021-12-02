package com.lee.dynamic.scheduler;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 定时任务操作
 */
@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobServer jobServer;

    /**
     * 添加定时任务
     */
    @PostMapping("/add")
    public String add(@RequestBody JobBean job) throws SchedulerException {
        return jobServer.add(job);
    }
}
