package ArtConcurrentBook.threadPool;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 执行定时任务的线程池
 *
 * @author Lee
 */
public class ScheduledThreadPool {
    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(5);//5个线程
        System.out.println("添加任务：" + LocalDateTime.now());
        // once(service);
        many(service);
    }
    /**
     * 执行一次的定时任务
     */
    public static void once(ScheduledExecutorService service) {
        service.schedule(() -> System.out.println("执行任务:"+ LocalDateTime.now()),3, TimeUnit.SECONDS);//推迟3秒执行
    }

    public static void many(ScheduledExecutorService service) {
        service.scheduleWithFixedDelay(() -> {
            System.out.println("执行任务:"+ LocalDateTime.now()+"线程名"+Thread.currentThread().getName());
            try {
                Thread.sleep(1000*2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },3,2,TimeUnit.SECONDS);
    }
}
