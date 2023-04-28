package com.lee.dynamic;

import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * 动态定时任务
 */
// @Configuration
// @EnableScheduling
@RestController
public class dynaminCron implements SchedulingConfigurer {
    String cron = "0/5 * * * * ?";

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
       taskRegistrar.addTriggerTask(
               //1.添加任务内容(Runnable)
               () -> System.out.println("执行定时任务2: " + LocalDateTime.now().toLocalTime()),
               //2.设置执行周期(Trigger)
               triggerContext -> {
                   //2.1 从数据库获取执行周期
                   // String cron = cronMapper.getCron();
                   //2.2 合法性校验.
                   if (StringUtils.hasLength(cron)) {
                       // Omitted Code ..
                   }
                   //2.3 返回执行周期(Date)
                   return new CronTrigger(cron).nextExecutionTime(triggerContext);
               }
       );
    }

    @PostMapping("/")
    public String revise(@RequestBody String cron) {
        this.cron = cron;
        return this.cron;
    }

}
