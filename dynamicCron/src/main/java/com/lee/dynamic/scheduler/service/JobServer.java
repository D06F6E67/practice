package com.lee.dynamic.scheduler.service;

import com.lee.dynamic.scheduler.entity.ScheduleJob;

/**
 * 任务处理
 *
 * @author lee
 */
public interface JobServer {

    String add(ScheduleJob job);

    String update(ScheduleJob job);

    String delete(ScheduleJob job);

    String pause(ScheduleJob job);

    String resume(ScheduleJob job);

    String run(ScheduleJob job);
}
