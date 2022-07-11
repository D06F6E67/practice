package ArtConcurrentBook.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 单个线程的线程池
 *
 * 单个线程的线程池？为啥不直接创个线程？
 * 线程池的优点：
 * 1.复用线程：不必频繁创建销毁线程
 * 2.也提供了任务队列和拒绝策略
 *
 * @author Lee
 */
public class SingleThreadExecuto {
    public static void main(String[] args) {
        ExecutorService service= Executors.newSingleThreadExecutor();
        for (int i=0;i<5;i++){
            int finalI = i;
            service.submit(()->{
                System.out.println(finalI +"线程名"+Thread.currentThread().getName());//CPU只创建了1个线程，名称始终一样
            });
        }
    }
}
