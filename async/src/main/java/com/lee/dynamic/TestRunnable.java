package com.lee.dynamic;

import com.lee.async.TestTask;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Log4j2
@Component
public class TestRunnable implements Runnable {

    @Resource
    private TestTask testTask;
    @Autowired
    private Task task;

    @SneakyThrows
    @Override
    public void run() {
        // while (testTask.isRunning()) {
        //     try {
        //         System.out.println(testTask.isRunning());
        //         Thread.sleep(5 * 1000);
        //     } catch (InterruptedException e) {
        //         e.printStackTrace();
        //     }
        // }
        log.info(task.getValue());
        task.setRunNumber(task.getRunNumber() - 1);
    }
}
