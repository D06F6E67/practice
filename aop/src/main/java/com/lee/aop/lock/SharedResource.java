package com.lee.aop.lock;

import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lee
 */
@Service
public class SharedResource {

    private final List<Thread> threadList = new ArrayList<>();
    @Setter
    private volatile boolean isExecuting = false;


    public void add(Thread thread) {
        threadList.add(thread);
        thread.start();
    }

    public void stop() {
        threadList.forEach(Thread::stop);
    }

    public boolean isExecuting() {
        return isExecuting;
    }
}
