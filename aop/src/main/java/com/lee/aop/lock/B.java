package com.lee.aop.lock;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lee
 */
@Service
public class B {

    @Resource
    private SharedResource sharedResource;

    public List<Integer> executeB() throws InterruptedException {

        List<Integer> list = new ArrayList<>();
        if (sharedResource.isExecuting()) {
            System.out.println("A 正在执行");
            return list;
        }

        Thread thread = new Thread(() -> {
            try {
                System.out.println("B 开始查询");

                Thread.sleep(1000);

                List<Integer> data = new ArrayList<>();
                int length = (int) (Math.random() * 10) + 3;
                for (int i = 0; i < length; i++) {
                    data.add((int) (Math.random() * 10));
                }
                System.out.println("B 查询结束");

                data.forEach(s -> {
                    list.add(s);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ignored) {
                    }
                });
                System.out.println("B 处理结束");
            } catch (InterruptedException ignored) {
            }
        });
        sharedResource.add(thread);

        thread.join();
        if (sharedResource.isExecuting()) {
            System.out.println("A 正在执行");
            list.clear();
        }
        return list;
    }
}
