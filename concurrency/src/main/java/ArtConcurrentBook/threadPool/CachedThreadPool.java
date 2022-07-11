package ArtConcurrentBook.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 带缓存的线程池
 *
 * @author Lee
 */
public class CachedThreadPool {

    public static void main(String[] args) {
        //创建线程池
        ExecutorService service= Executors.newCachedThreadPool();
        //有50个任务
        for(int i=0;i<50;i++){
            int finalI = i;
            service.submit(()->{
                System.out.println(finalI +"线程名"+Thread.currentThread().getName());//线程名有多少个，CPU就创建了多少个线程
            });
        }
    }
}
