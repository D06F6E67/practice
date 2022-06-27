package com.lee.dynamic;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

@Log4j2
@Component
public class Task implements CommandLineRunner {

    private Queue<Integer> queue = new LinkedList();
    private Integer number = 0;
    private Integer runNumber = 0;
    @Autowired
    private TestRunnable testRunnable;

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            Integer number = queue.size() / 10;
            for (; runNumber < number; runNumber++) {
                new Thread(testRunnable).start();
            }
            // number = 0;
            log.info(getValue());
            Thread.sleep(5 * 1000);
        }
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getRunNumber() {
        return runNumber;
    }

    synchronized public void setRunNumber(Integer runNumber) {
        this.runNumber = runNumber;
    }

    public void addValue(Integer value) {
        queue.add(value);
    }

    synchronized public Integer getValue() {
        return queue.poll();
    }

    // public Integer
}
