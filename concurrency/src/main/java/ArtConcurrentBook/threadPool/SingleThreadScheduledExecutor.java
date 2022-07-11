package ArtConcurrentBook.threadPool;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 执行定时任务的单个线程的线程池
 *
 * @author Lee
 */
public class SingleThreadScheduledExecutor {
    public static void main(String[] args) {
        ScheduledExecutorService service= Executors.newSingleThreadScheduledExecutor();
        System.out.println("添加任务："+ LocalDateTime.now());
        service.schedule(new Runnable() {

            @Override
            public void run() {
                System.out.println("执行任务："+LocalDateTime.now());
            }
        },3, TimeUnit.SECONDS);//推迟3秒执行任务
    }
}
